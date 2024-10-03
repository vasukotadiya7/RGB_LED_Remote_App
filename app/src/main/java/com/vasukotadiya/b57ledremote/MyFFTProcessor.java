package com.vasukotadiya.b57ledremote;

import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.util.fft.FFT;
import be.tarsos.dsp.AudioEvent;

public class MyFFTProcessor implements AudioProcessor {
    private FFT fft;
    private float[] buffer;
    private float[] magnitudeSpectrum;

    public MyFFTProcessor(int fftSize) {
        fft = new FFT(fftSize);
        buffer = new float[fftSize];
        magnitudeSpectrum = new float[fftSize / 2];
    }

    @Override
    public boolean process(AudioEvent audioEvent) {
        float[] audioBuffer = audioEvent.getFloatBuffer();
        fft.forwardTransform(audioBuffer);
        fft.modulus(audioBuffer, magnitudeSpectrum);

        // Process magnitude spectrum to detect beats (simple peak detection)
        detectBeats(magnitudeSpectrum);

        return true;
    }

    private void detectBeats(float[] spectrum) {
        // Implement a simple peak detection algorithm
        for (int i = 1; i < spectrum.length - 1; i++) {
            if (spectrum[i] > spectrum[i - 1] && spectrum[i] > spectrum[i + 1]) {
                // Peak detected (potential beat)
                System.out.println("Beat detected at frequency: " + i);
            }
        }
    }
//    private void detectBeats(float[] spectrum) {
//        float flux = 0.0f;
//        for (int i = 0; i < spectrum.length; i++) {
//            float delta = spectrum[i] - prevSpectrum[i];
//            flux += delta > 0 ? delta : 0;
//        }
//
//        // Apply threshold to detect beat
//        if (flux > THRESHOLD) {
//            System.out.println("Beat detected with flux: " + flux);
//        }
//
//        // Store current spectrum for comparison with next frame
//        System.arraycopy(spectrum, 0, prevSpectrum, 0, spectrum.length);
//    }


    @Override
    public void processingFinished() {
    }
}
