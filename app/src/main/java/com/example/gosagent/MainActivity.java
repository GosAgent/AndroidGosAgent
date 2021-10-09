package com.example.gosagent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;
    Button button12;
    Button button13;
    Button button14;
    Button button15;
    Button button16;
    Button button17;

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
                int typePage;
                switch (view.getId()) { //toDo...
                    case R.id.type1: typePage = 1; break;
                    case R.id.type2: typePage = 2; break;
                    case R.id.type3: typePage = 3; break;
                    case R.id.type4: typePage = 4; break;
                    case R.id.type5: typePage = 5; break;
                    case R.id.type6: typePage = 6; break;
                    case R.id.type7: typePage = 7; break;
                    case R.id.type8: typePage = 8; break;
                    case R.id.type9: typePage = 9; break;
                    case R.id.type10: typePage = 10; break;
                    case R.id.type11: typePage = 11; break;
                    case R.id.type12: typePage = 12; break;
                    case R.id.type13: typePage = 13; break;
                    case R.id.type14: typePage = 14; break;
                    case R.id.type15: typePage = 15; break;
                    case R.id.type16: typePage = 16; break;
                    case R.id.type17: typePage = 17; break;
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