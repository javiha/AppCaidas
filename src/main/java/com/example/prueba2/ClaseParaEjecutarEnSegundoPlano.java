package com.example.prueba2;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class ClaseParaEjecutarEnSegundoPlano extends Service {
    private SensorManager sensorManager;
    private long lastUpdate;
    SensorEventListener listen;
    String telefonos;
    AppCaidas principal;
    private long last_update = 0, last_movement = 0;
    private float prevX = 0, prevY = 0, prevZ = 0;
    private float curX = 0, curY = 0, curZ = 0;



    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        sensorManager = (SensorManager) getApplicationContext().getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
        listen = new SensorListen();
        Sensor accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(listen, accel, SensorManager.SENSOR_DELAY_NORMAL);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement

        synchronized (this) {
            long current_time = event.timestamp;
            //Log.d ("Tiempo", String.valueOf(event.timestamp));



            curX = event.values[0];
            curY = event.values[1];
            curZ = event.values[2];

            if (prevX == 0 && prevY == 0 && prevZ == 0) {
                last_update = current_time;
                last_movement = current_time;
                prevX = curX;
                prevY = curY;
                prevZ = curZ;
            }
            current_time = event.timestamp;
           // Log.d ("Tiempo2", String.valueOf(event.timestamp));
            long time_difference = current_time - last_update;
          // Log.d ("Difere", String.valueOf(time_difference));
            if (time_difference > 10000000) {

                //Log.d("Acele X", String.valueOf(curX-prevX));
                //Log.d("Acele Y", String.valueOf(curY-prevY));
                //Log.d("Acele Z", String.valueOf(curZ-prevZ));

                double Caida =Math.sqrt(Math.abs((curX-prevX)*(curX-prevX))+Math.abs((curY-prevY)*(curY-prevY))+Math.abs((curZ-prevZ)*(curZ-prevZ)));

               Log.d("Acele A", String.valueOf(Caida));

                if(Caida>13){
                    prevX = curX = 0;
                    prevY = curY = 0;
                    prevZ = curZ = 0;

                   // Log.d("Acele Def", String.valueOf(Caida));
                    onDestroy();
                    DeteCaida();}

                prevX = curX;
                prevY = curY;
                prevZ = curZ;
                last_update = current_time;





            }
        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        sensorManager.unregisterListener(listen);
        super.onDestroy();



    }

    public class SensorListen implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                getAccelerometer(event);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

    }

    public void DeteCaida() {
        onDestroy();
        Log.d("Caida","Caida");
        Intent detecaida = new Intent(this, Caida1.class);
        detecaida.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(detecaida);
    }


}
