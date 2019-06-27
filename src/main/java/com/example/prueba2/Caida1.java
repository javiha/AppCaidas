package com.example.prueba2;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.os.Vibrator;

public class Caida1 extends AppCompatActivity {

    private Vibrator vibrator;
    private static final int MILLIS_PER_SECOND = 1000;
    private static final int SECONDS_TO_COUNTDOWN = 30;
    private TextView     countdownDisplay;
    private CountDownTimer timer;
    int i=0;


    private void showTimer(int countdownMillis) {
        if(timer != null) { timer.cancel(); }
        timer = new CountDownTimer(countdownMillis, MILLIS_PER_SECOND)

        {
            @Override
            public void onTick(long millisUntilFinished) {
                countdownDisplay.setText("" +
                        millisUntilFinished / MILLIS_PER_SECOND);
            }
            @Override
            public void onFinish() {
                vibrator.cancel();
                if (i==0){
                Intent i = new Intent(getApplicationContext(), Caida2.class);
                startActivity(i);}
            }

        }.start();

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caida1);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {400, 600, 400,600};
        vibrator.vibrate(pattern, 0);
        Log.d("I",String.valueOf(i));

        countdownDisplay = (TextView) findViewById(R.id.time_display_box);
        try {
            showTimer(SECONDS_TO_COUNTDOWN * MILLIS_PER_SECOND);
        } catch (NumberFormatException e) {

        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }


    public void Caida(View v) {
        timer.cancel();
        timer = null;
        i=1;
        vibrator.cancel();
        Intent caida = new Intent(this, Caida2.class);
        startActivity(caida);
    }

    public void FalsaCaida(View v) {
        timer.cancel();
        timer=null;
        i=1;
        vibrator.cancel();
        Log.d("I",String.valueOf(i));
        Intent falsacaida = new Intent(this, AppCaidas.class);
        startActivity(falsacaida);
    }
    @Override
    public void onBackPressed() {

        }

}
