package com.example.gustavo.coacroch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DefineParams extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    static  int secondsLeft;
    static List<Character> levelList;
    static List<Integer> regularityList;
    boolean regularity;
    boolean level;
    int regularityCounter;
    int levelCounter;

    void toogleAlphaLevel(Button b, char difficulty) {
        float vibility;
        vibility = b.getAlpha();
        if (vibility == 0.2f) {
            levelCounter++;
            b.setAlpha(1f);
            levelList.add(difficulty);
            return;
        } else {
            levelCounter--;
            b.setAlpha(0.2f);
            levelList.remove(levelList.indexOf(difficulty));
        }
    }

    void toogleAlphaRegularity(Button b ,int number) {
        float vibility;
        vibility = b.getAlpha();
        if (vibility == 0.2f) {
            regularityCounter++;
            b.setAlpha(1f);
            regularityList.add(number);
            return;
        } else {
            regularityCounter--;
            b.setAlpha(0.2f);
            regularityList.remove(regularityList.indexOf(number));
        }

    }



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_params);
        regularityList =new ArrayList();
        regularityCounter=0;
        levelCounter=0;
        regularity = false;
        level = false;
        levelList =new ArrayList<>();

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.textView);
        Button btn = (Button) findViewById(R.id.button);
        seekBar.setProgress(30);
        textView.setText("0:30");
        final Intent intent = new Intent(this, ptMain.class);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                secondsLeft=progress;
                String displayedText = "";
                Double result = Math.floor(progress / 60);
                int intResult = result.intValue();
                displayedText = Integer.toString(intResult);
                int result2 =progress%60;

                String displayedText2= Integer.toString(result2);
                if(result2<10){
                    displayedText2= "0"+displayedText2;
                }
                displayedText=displayedText+":"+displayedText2;
                textView.setText(displayedText);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        Button regular = (Button) findViewById(R.id.reg);
        regular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaRegularity((Button)v,0);
            }
        });
        Button irregular = (Button) findViewById(R.id.irreg);
        irregular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaRegularity((Button)v,1);
            }
        });
        Button facil = (Button) findViewById(R.id.facil);
        facil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaLevel((Button) v,'F');
            }
        });
        Button medio = (Button) findViewById(R.id.medio);
        medio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaLevel((Button) v,'M');
            }
        });
        Button dificil = (Button) findViewById(R.id.difícil);
        dificil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaLevel((Button) v,'D');
            }
        });
        Button muitoDificil = (Button) findViewById(R.id.muitodificil);
        muitoDificil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toogleAlphaLevel((Button) v,'V');
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

    }

}
