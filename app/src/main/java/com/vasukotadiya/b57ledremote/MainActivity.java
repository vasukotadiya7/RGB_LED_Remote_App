package com.vasukotadiya.b57ledremote;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.ConsumerIrManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ConsumerIrManager irManager;
    public static HashMap<Integer,int[]> remoteData =new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        irManager=(ConsumerIrManager) this.getSystemService(Context.CONSUMER_IR_SERVICE);

        remoteData.put(0,new int[]{9098,4549,525,578,552,578,552,578,552,578,552,578,552,578,552,578,552,578,552,1682,552,1709,552,1709,552,1709,552,578,552,1682,552,1709,552,1709,552,578,552,578,552,578,525,604,525,578,552,578,552,578,552,578,552,1709,552,1709,525,1709,552,1709,552,1709,552,1709,552,1709,525,1709,552,414510});
        remoteData.put(1,new int[]{9098,4549,552,578,552,578,525,578,552,578,578,552,552,578,552,578,552,578,552,1709,552,1682,552,1709,552,1709,552,578,552,1709,525,1709,552,1709,552,1709,552,578,552,578,552,578,552,578,525,578,552,578,552,578,552,578,552,1709,552,1709,525,1709,552,1709,552,1709,552,1709,552,1682,552,275297});
        remoteData.put(2,new int[]{9098,4549,525,578,552,578,552,578,552,578,552,578,552,578,552,578,552,578,552,1682,552,1709,552,1709,552,1709,552,578,552,1682,552,1709,552,1709,552,578,552,1709,525,578,552,578,552,578,552,578,552,578,552,578,552,1709,552,578,525,1709,552,1709,578,1682,552,1709,552,1682,552,1709,578,385479});
        remoteData.put(3,new int[]{9098,4549,499,604,525,604,552,578,525,604,552,578,525,604,525,604,552,578,525,1709,552,1709,552,1709,552,1709,525,604,525,1709,552,1709,552,1709,552,1709,552,1682,525,604,525,604,552,578,552,578,552,578,552,578,525,604,525,604,525,1709,525,1735,552,1709,552,1709,525,1709,552,1709,552,389870});
        remoteData.put(4,new int[]{9072,4549,525,578,552,578,525,604,552,578,552,578,552,578,552,578,552,578,525,1709,552,1709,552,1709,552,1709,525,604,525,1709,552,1709,525,1735,552,578,552,578,552,1682,552,578,552,578,552,578,552,578,525,604,552,1709,552,1682,552,578,552,1709,552,1709,552,1709,525,1709,525,1735,552,425397});
        remoteData.put(5,new int[]{9072,4549,552,578,552,578,525,604,525,578,552,578,552,578,552,578,552,578,552,1709,552,1682,552,1709,552,1709,552,578,552,1709,552,1709,525,1709,552,578,552,1709,552,1709,525,604,525,578,552,578,552,578,552,578,552,1709,552,578,552,578,552,1682,552,1709,552,1709,552,1709,552,1709,525,225754});
        remoteData.put(6,new int[]{9098,4549,552,578,552,578,552,578,525,578,552,578,525,604,552,578,552,578,525,1735,525,1735,525,1709,552,1709,552,578,552,1709,552,1709,499,1735,552,1709,552,578,552,1709,552,578,525,578,552,578,552,578,552,578,552,578,552,1709,552,578,552,1709,525,1709,552,1709,578,1682,552,1709,525,359261});
        remoteData.put(7,new int[]{9045,4575,552,604,499,604,525,604,525,578,552,578,552,578,525,604,552,604,499,1735,552,1709,499,1735,552,1709,552,578,525,1735,525,1709,552,1709,525,1735,552,1709,552,1709,499,604,552,578,552,578,552,578,552,578,552,578,525,604,552,578,552,1682,525,1735,525,1735,525,1735,525,1735,499,332018});
        remoteData.put(8,new int[]{9098,4549,552,578,525,604,525,578,578,552,552,578,552,578,552,578,552,578,552,1682,552,1709,552,1709,552,1709,552,578,552,1709,499,1735,552,1709,552,578,552,578,552,578,552,1682,578,552,552,578,552,578,552,578,552,1709,552,1709,525,1709,552,578,552,1709,552,1709,552,1709,525,1709,552,226201});
        remoteData.put(9,new int[]{9045,4601,499,631,473,631,499,631,499,631,499,631,499,604,525,631,499,631,499,1735,499,1761,499,1761,499,1735,525,631,499,1735,499,1735,525,1735,525,604,525,1735,525,604,499,1735,525,604,525,604,525,604,525,604,525,1735,525,604,525,1709,552,604,525,1709,525,1761,499,1735,499,1735,552,385452});
        remoteData.put(10,new int[]{9098,4522,552,578,552,578,552,578,552,578,552,578,525,578,552,578,552,578,552,1709,525,1735,552,1709,525,1709,552,578,552,1709,552,1709,552,1682,552,1709,578,552,552,578,552,1709,552,578,552,578,525,604,552,552,552,578,578,1682,552,1709,552,578,552,1682,552,1709,552,1709,552,1709,552,294020});
        remoteData.put(11,new int[]{9019,4628,525,631,499,631,499,604,499,631,499,631,499,631,499,631,499,631,525,1709,525,1735,499,1761,499,1761,499,631,499,1735,525,1735,499,1761,499,1761,473,1788,499,604,525,1735,499,604,525,631,499,604,525,631,499,631,552,552,499,1761,499,631,525,1735,525,1709,552,1709,525,1735,499,384532});
        remoteData.put(12,new int[]{9072,4549,552,578,552,578,525,578,552,578,552,578,552,578,552,578,552,578,552,1709,525,1709,552,1709,552,1709,552,578,525,1735,525,1709,552,1709,552,578,552,578,552,1709,525,1709,552,578,525,604,552,578,552,578,552,1709,552,1709,525,604,525,578,552,1709,552,1709,552,1709,525,1709,552,361496});
        remoteData.put(13,new int[]{9045,4575,525,604,525,604,525,604,499,604,552,578,525,604,552,578,525,604,525,1735,525,1735,525,1709,552,1709,525,604,552,1709,552,1709,499,1735,525,604,552,1709,552,1709,552,1682,525,604,552,578,525,604,552,578,525,1735,552,578,552,578,525,578,552,1709,525,1735,552,1709,552,1709,525,302198});
        remoteData.put(14,new int[]{9098,4522,552,578,552,578,552,578,552,578,552,578,552,578,552,578,525,578,552,1709,552,1709,552,1709,552,1709,525,578,552,1709,552,1709,552,1709,552,1682,552,578,552,1709,552,1709,552,578,552,578,552,578,552,578,525,578,552,1709,552,578,552,578,552,1709,552,1682,552,1709,552,1709,552,410302});
        remoteData.put(15,new int[]{9072,4575,499,631,499,604,552,578,525,604,525,604,499,604,525,604,525,604,525,1735,525,1735,525,1735,525,1709,525,604,525,1735,552,1709,552,1709,525,1709,525,1735,552,1709,552,1709,525,578,525,604,552,578,552,578,552,578,525,604,525,604,525,604,525,1709,552,1709,552,1709,552,1709,525,321289});
        remoteData.put(16,new int[]{9098,4522,552,578,552,578,552,578,552,578,525,578,552,578,552,578,552,578,552,1709,552,1709,552,1682,552,1709,552,578,552,1709,552,1709,552,1682,552,578,552,1709,552,578,552,578,552,1709,552,552,552,578,552,578,552,1709,552,578,552,1709,552,1682,552,578,552,1709,552,1709,552,1709,552,307115});
        remoteData.put(17,new int[]{9098,4522,552,578,552,578,552,578,552,578,552,578,552,578,552,578,525,578,552,1709,552,1709,552,1709,578,1656,552,578,552,1709,552,1709,552,1709,552,1709,525,578,552,578,552,578,552,1709,552,578,552,578,525,604,525,578,552,1709,578,1682,578,1682,552,578,552,1682,552,1709,552,1709,552,341011});
        remoteData.put(18,new int[]{9098,4522,552,578,552,578,552,578,552,578,552,578,552,578,552,578,525,604,525,1709,552,1709,552,1709,552,1709,525,578,552,1709,552,1709,552,1709,552,578,552,578,525,578,552,578,552,1709,552,578,552,578,552,578,552,1682,552,1709,578,1682,552,1709,578,552,552,1682,552,1709,552,1709,552,347770});
        remoteData.put(19,new int[]{9045,4549,525,631,499,604,525,604,525,604,525,604,525,631,499,604,525,604,499,1735,525,1761,499,1735,525,1709,525,604,552,1709,552,1709,552,1709,525,1735,525,1709,552,578,552,578,525,1735,552,578,525,604,525,578,525,604,552,578,552,1709,552,1709,552,578,525,1709,552,1709,552,1709,552,269564});
        remoteData.put(20,new int[]{9072,4549,552,578,552,578,552,578,525,604,525,578,552,578,552,578,552,578,552,1709,525,1735,525,1709,552,1709,525,604,552,1709,552,1709,525,1709,552,578,552,1709,552,1709,552,578,525,1709,552,578,578,552,552,578,552,1709,552,578,552,578,525,1709,552,578,552,1709,552,1709,552,1709,525,396181});
        remoteData.put(21,new int[]{9072,4575,525,604,499,604,552,578,525,604,525,604,525,604,525,604,525,604,525,1735,525,1709,525,1735,525,1735,525,604,552,1682,525,1735,525,1735,525,1735,525,604,552,1682,525,604,525,1735,525,604,525,604,525,604,525,604,525,1735,525,578,525,1761,499,604,552,1709,525,1735,525,1709,525,404412});
        remoteData.put(22,new int[]{9098,4522,552,578,552,578,552,578,552,578,552,578,552,578,525,578,552,578,552,1709,552,1709,552,1709,552,1682,552,578,552,1709,552,1709,552,1709,525,578,552,578,552,578,552,578,552,1709,552,578,552,578,552,578,552,1682,552,1709,552,1709,552,1709,525,578,552,1709,552,1709,552,1709,552,280319});
        remoteData.put(23,new int[]{9098,4522,552,578,525,604,552,578,552,578,552,578,552,578,499,604,552,578,552,1709,552,1709,552,1709,525,1709,552,578,552,1709,552,1709,552,1709,525,1709,552,1709,552,1709,552,578,552,1709,525,578,552,578,552,578,552,578,552,578,552,578,552,1709,525,604,525,1709,552,1709,552,1709,552,278978});


        Button onButton = findViewById(R.id.button_on);
        Button offButton = findViewById(R.id.button_off);
        Button brightnessUpButton = findViewById(R.id.button_brightness_up);
        Button brightnessDownButton = findViewById(R.id.button_brightness_down);
        Button whiteButton = findViewById(R.id.button_w);
        Button flashButton = findViewById(R.id.button_flash);
        Button strobeButton = findViewById(R.id.button_strobe);
        Button fadeButton = findViewById(R.id.button_fade);
        Button smoothButton = findViewById(R.id.button_smooth);
        Button redButton = findViewById(R.id.button_r);
        Button greenButton = findViewById(R.id.button_g);
        Button blueButton = findViewById(R.id.button_b);
        Button r1Button = findViewById(R.id.button_r1);
        Button r2Button = findViewById(R.id.button_r2);
        Button r3Button = findViewById(R.id.button_r3);
        Button r4Button = findViewById(R.id.button_r4);
        Button g1Button = findViewById(R.id.button_g1);
        Button g2Button = findViewById(R.id.button_g2);
        Button g3Button = findViewById(R.id.button_g3);
        Button g4Button = findViewById(R.id.button_g4);
        Button b1Button = findViewById(R.id.button_b1);
        Button b2Button = findViewById(R.id.button_b2);
        Button b3Button = findViewById(R.id.button_b3);
        Button b4Button = findViewById(R.id.button_r4);

        Button musicActivity= findViewById(R.id.musicsync);

//        Button btnShortcut=findViewById(R.id.buttonShortcut);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (!irManager.hasIrEmitter()) {
//                Toast.makeText(this, "No IR Emitter found!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//        }

        brightnessUpButton.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(0)));
        brightnessDownButton.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(1)));
        offButton.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(2)));
        onButton.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(3)));
        redButton.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(4)));
        greenButton.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(5)));
        blueButton.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(6)));
        whiteButton.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(7)));
        r1Button.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(8)));
        g1Button.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(9)));
        b1Button.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(10)));
        flashButton.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(11)));
        r2Button.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(12)));
        g2Button.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(13)));
        b2Button.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(14)));
        strobeButton.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(15)));
        r3Button.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(16)));
        g3Button.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(17)));
        b3Button.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(18)));
        fadeButton.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(19)));
        r4Button.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(20)));
        g4Button.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(21)));
        b4Button.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(22)));
        smoothButton.setOnClickListener(v -> irManager.transmit(38000,remoteData.get(23)));

        musicActivity.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,MusicActivity.class)));

//        btnShortcut.setOnClickListener( view -> {
//            Intent intent = new Intent("android.settings.ACTION_QUICK_SETTINGS_SETTINGS");
//            startActivity(intent);
//
//        });
    }

//    private void sendIrSignal(String command) {
//        int frequency = 38000;
//        IRTransmitter irTransmitter=new IRTransmitter(this);
//
//        switch (command) {
//            case "on":
//
//                irTransmitter.transmitProntoHex(3);
//                break;
//            case "off":
//                irTransmitter.transmitProntoHex(2);
//                break;
//            case "brightness_up":
//                irTransmitter.transmitProntoHex(0);
//                break;
//            case "brightness_down":
//                irTransmitter.transmitProntoHex(1);
//                break;
//            case "white":
//                irTransmitter.transmitProntoHex(7);
//                break;
//            case "flash":
//                irTransmitter.transmitProntoHex(11);
//                break;
//            case "strobe":
//                irTransmitter.transmitProntoHex(15);
//                break;
//            case "fade":
//                irTransmitter.transmitProntoHex(19);
//                break;
//            case "smooth":
//                irTransmitter.transmitProntoHex(23);
//                break;
//            case "red":
//                irTransmitter.transmitProntoHex(4);
//                break;
//            case "green":
//                irTransmitter.transmitProntoHex(5);
//                break;
//            case "blue":
//                irTransmitter.transmitProntoHex(6);
//                break;
//            case "r1":
//                irTransmitter.transmitProntoHex(8);
//                break;
//            case "r2":
//                irTransmitter.transmitProntoHex(12);
//                break;
//            case "r3":
//                irTransmitter.transmitProntoHex(16);
//                break;
//            case "r4":
//                irTransmitter.transmitProntoHex(20);
//                break;
//            case "g1":
//                irTransmitter.transmitProntoHex(9);
//                break;
//            case "g2":
//                irTransmitter.transmitProntoHex(13);
//                break;
//            case "g3":
//                irTransmitter.transmitProntoHex(17);
//                break;
//            case "g4":
//                irTransmitter.transmitProntoHex(21);
//                break;
//            case "b1":
//                irTransmitter.transmitProntoHex(10);
//                break;
//            case "b2":
//                irTransmitter.transmitProntoHex(14);
//                break;
//            case "b3":
//                irTransmitter.transmitProntoHex(18);
//                break;
//            case "b4":
//                irTransmitter.transmitProntoHex(22);
//                break;
//            default:
////                irTransmitter.transmitProntoHex();
//                break;
//        }



    }




