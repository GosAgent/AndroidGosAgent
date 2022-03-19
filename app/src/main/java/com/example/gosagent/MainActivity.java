package com.example.gosagent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageButton button1;
    ImageButton button2;
    ImageButton button3;
    ImageButton button4;
    ImageButton button5;
    ImageButton button6;
    ImageButton button7;
    ImageButton button8;
    ImageButton button9;
    ImageButton button10;
    ImageButton button11;
    ImageButton button12;
    ImageButton button13;
    ImageButton button14;
    ImageButton button15;
    ImageButton button16;
    ImageButton button17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.type1);
        button2 = findViewById(R.id.type2);
        button3 = findViewById(R.id.type3);
        button4 = findViewById(R.id.type4);
        button5 = findViewById(R.id.type5);
        button6 = findViewById(R.id.type6);
        button7 = findViewById(R.id.type7);
        button8 = findViewById(R.id.type8);
        button9 = findViewById(R.id.type9);
        button10 = findViewById(R.id.type10);
        button11 = findViewById(R.id.type11);
        button12 = findViewById(R.id.type12);
        button13 = findViewById(R.id.type13);
        button14 = findViewById(R.id.type14);
        button15 = findViewById(R.id.type15);
        button16 = findViewById(R.id.type16);
        button17 = findViewById(R.id.type17);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.type1: PluginCore.MarkerType = 1; break;
                    case R.id.type2: PluginCore.MarkerType = 2; break;
                    case R.id.type3: PluginCore.MarkerType = 3; break;
                    case R.id.type4: PluginCore.MarkerType = 4; break;
                    case R.id.type5: PluginCore.MarkerType = 5; break;
                    case R.id.type6: PluginCore.MarkerType = 6; break;
                    case R.id.type7: PluginCore.MarkerType = 7; break;
                    case R.id.type8: PluginCore.MarkerType = 8; break;
                    case R.id.type9: PluginCore.MarkerType = 9; break;
                    case R.id.type10: PluginCore.MarkerType = 10; break;
                    case R.id.type11: PluginCore.MarkerType = 11; break;
                    case R.id.type12: PluginCore.MarkerType = 12; break;
                    case R.id.type13: PluginCore.MarkerType = 13; break;
                    case R.id.type14: PluginCore.MarkerType = 14; break;
                    case R.id.type15: PluginCore.MarkerType = 15; break;
                    case R.id.type16: PluginCore.MarkerType = 16; break;
                    case R.id.type17: PluginCore.MarkerType = 17; break;
                }
                Intent intent = new Intent(".MapsActivity");
                startActivity(intent);
            }
        };

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        button7.setOnClickListener(onClickListener);
        button8.setOnClickListener(onClickListener);
        button9.setOnClickListener(onClickListener);
        button10.setOnClickListener(onClickListener);
        button11.setOnClickListener(onClickListener);
        button12.setOnClickListener(onClickListener);
        button13.setOnClickListener(onClickListener);
        button14.setOnClickListener(onClickListener);
        button15.setOnClickListener(onClickListener);
        button16.setOnClickListener(onClickListener);
        button17.setOnClickListener(onClickListener);
    }
}