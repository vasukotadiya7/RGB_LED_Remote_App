package com.vasukotadiya.b57ledremote;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.ConsumerIrManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.onsets.BeatRootSpectralFluxOnsetDetector;
import be.tarsos.dsp.onsets.ComplexOnsetDetector;
import be.tarsos.dsp.onsets.OnsetHandler;
import be.tarsos.dsp.onsets.OnsetDetector;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.util.fft.FFT;

public class MusicActivity extends AppCompatActivity {

    private AudioRecord audioRecord;
    private int bufferSize;
    private boolean isRecording = false;
    private ConsumerIrManager irManager;
    IRTransmitter irTransmitter;

    private int[] colorCodes = {
            4, // Red
            12, // Orange
            20, // Yellow
            5, // Green
            6, // Blue
            23,  // Purple
            13
    };

    private AudioDispatcher dispatcher;
    private boolean isBeatDetected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        irManager = (ConsumerIrManager) getSystemService(Context.CONSUMER_IR_SERVICE);
        if (!irManager.hasIrEmitter()) {
            Toast.makeText(this, "No IR Emitter found!", Toast.LENGTH_LONG).show();
            return;
        }

        irTransmitter=new IRTransmitter(this);

        bufferSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);
//        audioRecord.startRecording();
//        startAnalyzing();

        startBeatDetection();

    }
    private void startAnalyzing() {
        audioRecord.startRecording();
        isRecording = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                short[] buffer = new short[bufferSize];
                while (isRecording) {
                    int result = audioRecord.read(buffer, 0, bufferSize);
                    if (result > 0) {
                        analyzeBuffer(buffer); // Process the buffer to detect beats
                    }
                }
            }
        }).start();
    }

    private void analyzeBuffer(short[] buffer) {
        // Simple beat detection by measuring sound amplitude
        long sum = 0;
        for (short sample : buffer) {
            sum += Math.abs(sample);
        }
        long average = sum / buffer.length;

        // Arbitrary threshold for detecting a beat or high sound level
        if (average > 1000) {
            // Detected a beat, now transmit an IR signal to change the LED light
            irTransmitter.transmitProntoHex(11);
//            transmitIrSignal("F740BF");  // Example code for ON button
        }
    }
    private void startBeatDetection() {
        // Set up audio input from the microphone
        dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(44100, 7168, 512);
//        dispatcher.addAudioProcessor(new MyFFTProcessor(7168));

        // Set up an OnsetProcessor to detect beats
        OnsetHandler onsetHandler=new OnsetHandler() {
            @Override
            public void handleOnset(double v, double v1) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        processBeat();
                    }
                });
            }
        };
//        ComplexOnsetDetector onsetDetector=new BeatRootSpectralFluxOnsetDetector(7168);
//        onsetDetector.setHandler(onsetHandler);
//        BeatRootSpectralFluxOnsetDetector beatRootSpectralFluxOnsetDetector=new BeatRootSpectralFluxOnsetDetector(dispatcher,1024,512);
//        beatRootSpectralFluxOnsetDetector.setHandler(onsetHandler);

        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult res, AudioEvent e){
                final float pitchInHz = res.getPitch();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        processPitch(pitchInHz);
                        Log.d("Pitch Checking", "run: "+pitchInHz);
                    }
                });
            }
        };

        AudioProcessor pitchProcessor = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 44100, 7168, pdh);
        dispatcher.addAudioProcessor(pitchProcessor);

//        Thread audioThread = new Thread(dispatcher, "Audio Thread");
//        audioThread.start();

        // Add beat detection to the dispatcher
//        FFT fft = new FFT(1024);
//        dispatcher.addAudioProcessor(fft);
//
//        dispatcher.addAudioProcessor((AudioProcessor) onsetDetector);

        // Start the dispatcher in a new thread
        new Thread(dispatcher, "Audio Dispatcher").start();
    }
    public void processBeat() {
        // Cycle through different colors on each beat
        int randomIndex = new Random().nextInt(colorCodes.length);
        irTransmitter.transmitProntoHex(colorCodes[randomIndex]);
    }

    public void processPitch(float pitchInHz) {

        if (pitchInHz!=-1.0){

    int v=new Random().nextInt(colorCodes.length);
    irTransmitter.transmitProntoHex(colorCodes[v]);
        }
//if (pitchInHz==-1.0){
//    irTransmitter.transmitProntoHex(2);
//
//}
//else{
//    irTransmitter.transmitProntoHex(3);
//}
//        if(pitchInHz >= 1 && pitchInHz < 100) {
//            //
//            irTransmitter.transmitProntoHex(colorCodes[0]);
//        }
//        else if(pitchInHz >= 100 && pitchInHz < 200) {
//            //B
//            irTransmitter.transmitProntoHex(colorCodes[1]);
//        }
//        else if(pitchInHz >= 200 && pitchInHz < 300) {
//            //C
//            irTransmitter.transmitProntoHex(colorCodes[2]);
//        }
//        else if(pitchInHz >= 300 && pitchInHz < 400) {
//            //D
//            irTransmitter.transmitProntoHex(colorCodes[3]);
//        }
//        else if(pitchInHz >= 400 && pitchInHz <= 500) {
//            //E
//            irTransmitter.transmitProntoHex(colorCodes[4]);
//        }
//        else if(pitchInHz >= 500 && pitchInHz < 600) {
//            //F
//            irTransmitter.transmitProntoHex(colorCodes[5]);
//        }
//        else if(pitchInHz >= 185 && pitchInHz < 196) {
//            //G
//            irTransmitter.transmitProntoHex(colorCodes[0]);
//        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRecording = false;

//        audioRecord.stop();
//        audioRecord.release();
    }

}
