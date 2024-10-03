package com.vasukotadiya.b57ledremote;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ConsumerIrManager irManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        irManager=(ConsumerIrManager) this.getSystemService(Context.CONSUMER_IR_SERVICE);


        Button onButton = findViewById(R.id.onButton);
        Button offButton = findViewById(R.id.offButton);
        Button brightnessUpButton = findViewById(R.id.brightnessUpButton);
        Button brightnessDownButton = findViewById(R.id.brightnessDownButton);
        Button whiteButton = findViewById(R.id.whiteButton);
        Button flashButton = findViewById(R.id.flashButton);
        Button strobeButton = findViewById(R.id.strobeButton);
        Button fadeButton = findViewById(R.id.fadeButton);
        Button smoothButton = findViewById(R.id.smoothButton);
        Button redButton = findViewById(R.id.redButton);
        Button greenButton = findViewById(R.id.greenButton);
        Button blueButton = findViewById(R.id.blueButton);
        Button r1Button = findViewById(R.id.r1Button);
        Button r2Button = findViewById(R.id.r2Button);
        Button r3Button = findViewById(R.id.r3Button);
        Button r4Button = findViewById(R.id.r4Button);
        Button g1Button = findViewById(R.id.g1Button);
        Button g2Button = findViewById(R.id.g2Button);
        Button g3Button = findViewById(R.id.g3Button);
        Button g4Button = findViewById(R.id.g4Button);
        Button b1Button = findViewById(R.id.b1Button);
        Button b2Button = findViewById(R.id.b2Button);
        Button b3Button = findViewById(R.id.b3Button);
        Button b4Button = findViewById(R.id.b4Button);

        Button musicActivity= findViewById(R.id.musicSync);

        onButton.setOnClickListener(v -> sendIrSignal("on"));
        offButton.setOnClickListener(v -> sendIrSignal("off"));
        brightnessUpButton.setOnClickListener(v -> sendIrSignal("brightness_up"));
        brightnessDownButton.setOnClickListener(v -> sendIrSignal("brightness_down"));
        whiteButton.setOnClickListener(v -> sendIrSignal("white"));
        flashButton.setOnClickListener(v -> sendIrSignal("flash"));
        strobeButton.setOnClickListener(v -> sendIrSignal("strobe"));
        fadeButton.setOnClickListener(v -> sendIrSignal("fade"));
        smoothButton.setOnClickListener(v -> sendIrSignal("smooth"));
        redButton.setOnClickListener(v -> sendIrSignal("red"));
        greenButton.setOnClickListener(v -> sendIrSignal("green"));
        blueButton.setOnClickListener(v -> sendIrSignal("blue"));
        r1Button.setOnClickListener(v -> sendIrSignal("r1"));
        r2Button.setOnClickListener(v -> sendIrSignal("r2"));
        r3Button.setOnClickListener(v -> sendIrSignal("r3"));
        r4Button.setOnClickListener(v -> sendIrSignal("r4"));
        g1Button.setOnClickListener(v -> sendIrSignal("g1"));
        g2Button.setOnClickListener(v -> sendIrSignal("g2"));
        g3Button.setOnClickListener(v -> sendIrSignal("g3"));
        g4Button.setOnClickListener(v -> sendIrSignal("g4"));
        b1Button.setOnClickListener(v -> sendIrSignal("b1"));
        b2Button.setOnClickListener(v -> sendIrSignal("b2"));
        b3Button.setOnClickListener(v -> sendIrSignal("b3"));
        b4Button.setOnClickListener(v -> sendIrSignal("b4"));

        musicActivity.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,MusicActivity.class)));
    }

    private void sendIrSignal(String command) {
        int frequency = 38000;
        IRTransmitter irTransmitter=new IRTransmitter(this);

        switch (command) {
            case "on":
                irTransmitter.transmitProntoHex(3);
                break;
            case "off":
                irTransmitter.transmitProntoHex(2);
                break;
            case "brightness_up":
                irTransmitter.transmitProntoHex(0);
                break;
            case "brightness_down":
                irTransmitter.transmitProntoHex(1);
                break;
            case "white":
                irTransmitter.transmitProntoHex(7);
                break;
            case "flash":
                irTransmitter.transmitProntoHex(11);
                break;
            case "strobe":
                irTransmitter.transmitProntoHex(15);
                break;
            case "fade":
                irTransmitter.transmitProntoHex(19);
                break;
            case "smooth":
                irTransmitter.transmitProntoHex(23);
                break;
            case "red":
                irTransmitter.transmitProntoHex(4);
                break;
            case "green":
                irTransmitter.transmitProntoHex(5);
                break;
            case "blue":
                irTransmitter.transmitProntoHex(6);
                break;
            case "r1":
                irTransmitter.transmitProntoHex(8);
                break;
            case "r2":
                irTransmitter.transmitProntoHex(12);
                break;
            case "r3":
                irTransmitter.transmitProntoHex(16);
                break;
            case "r4":
                irTransmitter.transmitProntoHex(20);
                break;
            case "g1":
                irTransmitter.transmitProntoHex(9);
                break;
            case "g2":
                irTransmitter.transmitProntoHex(13);
                break;
            case "g3":
                irTransmitter.transmitProntoHex(17);
                break;
            case "g4":
                irTransmitter.transmitProntoHex(21);
                break;
            case "b1":
                irTransmitter.transmitProntoHex(10);
                break;
            case "b2":
                irTransmitter.transmitProntoHex(14);
                break;
            case "b3":
                irTransmitter.transmitProntoHex(18);
                break;
            case "b4":
                irTransmitter.transmitProntoHex(22);
                break;
            default:
//                irTransmitter.transmitProntoHex();
                break;
        }



    }


}

