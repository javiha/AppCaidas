package com.example.prueba2;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;


class Utilidades{
    MainActivity mainActivity;

    public static String Telefono;
    public static String SMS;
    public static String SMS1;
    public static String latitude;
    public static String longitud;
    public static String Antecedentes;
    public static String Medicacion;
    public static String Nombre;
    public static String Hombre;
    public static String Mujer;
    public static int x;



}



public class AppCaidas extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String telefonos;
    private long last_update = 0, last_movement = 0;
    private float prevX = 0, prevY = 0, prevZ = 0;
    private float curX = 0, curY = 0, curZ = 0;
    TextView nivel;
    DaoContacto daoContacto;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        daoContacto = new DaoContacto(this);
        telefonos = String.valueOf(daoContacto.Telefonos());
        daoContacto.SMS();

        if(Utilidades.x ==0){
        Intent caida = new Intent(this, Ajustes.class);
        startActivity(caida);
        Utilidades.x=1;
        }

        Utilidades.Telefono=telefonos;

        startService(new Intent(this, ClaseParaEjecutarEnSegundoPlano.class));
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para realizar llamadas telefónicas.");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso!");
        }


            int permissionCheck2 = ContextCompat.checkSelfPermission(
                    this, android.Manifest.permission.SEND_SMS);
            if (permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
                Log.i("Mensaje", "No se tiene permiso para enviar SMS.");
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, 225);
            } else {
                Log.i("Mensaje", "Se tiene permiso para enviar SMS!");
            }

        int permissionCheck3 = ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permissionCheck3 != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para enviar SMS.");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso para enviar SMS!");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                Log.i("Permisos", "Se tienen los permisos!");
            } else {
                ActivityCompat.requestPermissions(
                        this, new String[] { android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION }, 1222);
            }
        }








        /* BATERIA*/
        nivel = (TextView) findViewById(R.id.txtNivel);

        BroadcastReceiver bateriaReciever = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                context.unregisterReceiver(this);
                int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int level = -1;
                if (currentLevel >= 0 && scale > 0) {
                    level = (currentLevel * 100) / scale;
                }
                nivel.setText( + level + "");

            }
        };
        IntentFilter batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(bateriaReciever, batteryFilter);

        NavigationView navigationView= (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finishAffinity();;
        }
    }

    public void Prueba(View v){
        String x = Utilidades.Antecedentes;
        if(x==null){
        Log.d("x", x+"x");}
    }



    public void Menu(View v) {
        Intent menu = new Intent(this, AppCaidas.class);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

            drawer.openDrawer(GravityCompat.START);


    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch(id) {

            case R.id.ContactoEmergencia:
                Intent contacto = new Intent(this, MainActivity.class);
                startActivity(contacto);
                break;

            case R.id.AjustesCuenta:
                Intent ajustes = new Intent(this, Ajustes.class);
                startActivity(ajustes);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





}
