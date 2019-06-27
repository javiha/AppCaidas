package com.example.prueba2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba2.ContactoActivity;
import com.example.prueba2.DaoContacto;

import java.util.concurrent.TimeUnit;

import static android.Manifest.permission_group.SMS;

public class Caida2 extends AppCompatActivity {

    private static final int MILLIS_PER_SECOND2 = 1000;
    private static final int SECONDS_TO_COUNTDOWN2 = 30;
    private TextView countdownDisplay2;
    private CountDownTimer timer2;
    private Vibrator vibrator;
    String telefonos;
    String SMS;
    String SMS1;
    String Sexo;
    Boolean Hombre, Mujer;
    int i =0, t=0;






    private void showTimer(int countdownMillis) {
        if(timer2 != null) { timer2.cancel(); }
        timer2 = new CountDownTimer(countdownMillis, MILLIS_PER_SECOND2) {
            @Override
            public void onTick(long millisUntilFinished) {
                countdownDisplay2.setText("" +
                        millisUntilFinished / MILLIS_PER_SECOND2);
            }
            @Override
            public void onFinish() {
                vibrator.cancel();
                try {
                    if (t==0){
                    Ayuda2();}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private class EndCallListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if(TelephonyManager.CALL_STATE_RINGING == state) {

            }
            if(TelephonyManager.CALL_STATE_OFFHOOK == state) {
                //wait for phone to go offhook (probably set a boolean flag) so you know your app initiated the call.
            }
            if(TelephonyManager.CALL_STATE_IDLE == state) {
                //when this state occurs, and your flag is set, restart your app
                if(i==1) {
                    Intent caida = new Intent(Caida2.this,AppCaidas.class);
                    startActivity(caida);
                }
                i=i+1;
            }
        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caida2);
        vibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {400, 600, 400,600};
        vibrator.vibrate(pattern, 0);


        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();



        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        double latitude = location.getLatitude();
        double longitud = location.getLongitude();
        Utilidades.latitude = String.valueOf(latitude);
        Utilidades.longitud = String.valueOf(longitud);


        Log.d("x", String.valueOf(latitude));
        Log.d("y", String.valueOf(longitud));
        Log.d ("Z", String.valueOf(location));

       countdownDisplay2 = (TextView) findViewById(R.id.time_display_box2);
        try {
            showTimer(SECONDS_TO_COUNTDOWN2 * MILLIS_PER_SECOND2);
        } catch (NumberFormatException e) {

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }
    public void Bien(View v) {
        t=1;
        timer2.cancel();
        vibrator.cancel();
        telefonos = Utilidades.Telefono;
        SMS = Utilidades.SMS;
        SMS1 = Utilidades.SMS1;
        Hombre=Boolean.valueOf(Utilidades.Hombre);
        Mujer=Boolean.valueOf(Utilidades.Mujer);

        if(Hombre==true){
            Sexo="Hombre";
        }
        if(Mujer==true){
            Sexo="Mujer";
        }
        String text = "Caida sin daños, Nombre: "+ Utilidades.Nombre+", "+Sexo+". Antecedentes: "+Utilidades.Antecedentes+". Medicacion: "+Utilidades.Medicacion+". https://maps.google.com/?q="+Utilidades.latitude+","+Utilidades.longitud;
        Log.d("sms",text);
        if(SMS==null){}else{
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(SMS, null, text , null, null);}

        if(SMS1==null){}else{
        SmsManager sms1 = SmsManager.getDefault();
        sms1.sendTextMessage(SMS1, null, text , null, null);}

        int requestCode = 0;
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + telefonos));
        startActivityForResult(callIntent,requestCode);
        EndCallListener callListener = new EndCallListener();
        TelephonyManager mTM = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        mTM.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);


    }
public void Ayuda(View v) throws InterruptedException {
        Ayuda2();
}


    public void Ayuda2() throws InterruptedException {
        t=1;
        Log.d("Prueba", String.valueOf(i));
        timer2.cancel();
        vibrator.cancel();
        SMS = Utilidades.SMS;
        SMS1 = Utilidades.SMS1;
        telefonos = String.valueOf(0);
        Hombre=Boolean.valueOf(Utilidades.Hombre);
        Mujer=Boolean.valueOf(Utilidades.Mujer);

        if(Hombre==true){
            Sexo="Hombre";
        }
        if(Mujer==true){
            Sexo="Mujer";
        }

        String text = "Caida con necesidad de asistencia,Nombre: "+ Utilidades.Nombre+","+Sexo+". Antecedentes: "+Utilidades.Antecedentes+". Medicacion: "+Utilidades.Medicacion+". https://maps.google.com/?q="+Utilidades.latitude+","+Utilidades.longitud;
        Log.d("sms",SMS + SMS1 + text);
        if(SMS==null){}else{
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(SMS, null, text , null, null);}

        if(SMS1==null){}else{
            SmsManager sms1 = SmsManager.getDefault();
            sms1.sendTextMessage(SMS1, null, text , null, null);}

        int requestCode = 0;
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + telefonos+61));
        startActivityForResult(callIntent,requestCode);
        EndCallListener callListener = new EndCallListener();
        TelephonyManager mTM = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
        mTM.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);






    }




    @Override
    public void onBackPressed() {


    }

}
