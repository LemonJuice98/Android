package com.example.administrator.mhw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Start extends AppCompatActivity {
    private LinearLayout StartLogo;
    private TextView StartText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        StartLogo = (LinearLayout) findViewById(R.id.Start_Logo);
        TimerTask task = new TimerTask(){
            public void run(){
                Intent intent = new Intent(Start.this, MainActivity.class);
                startActivity(intent);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 2000);
        beginAnimation();
    }

    private void beginAnimation() {
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(2000);
        StartLogo.startAnimation(alpha);
    }
}
