package com.example.prueba2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Ajustes extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos);

        if(Utilidades.x==1){
            Intent caida = new Intent(this, AppCaidas.class);
            startActivity(caida);
            Utilidades.x=2;
        }

        final Context context =this;
        final SharedPreferences sharprefs= getSharedPreferences("ArchivoSP", context.MODE_PRIVATE);
        final SharedPreferences sharprefs2= getSharedPreferences("ArchivoSP1", context.MODE_PRIVATE);
        final SharedPreferences sharprefs3= getSharedPreferences("ArchivoSP2", context.MODE_PRIVATE);
        final SharedPreferences sharprefs4= getSharedPreferences("ArchivoSP3", context.MODE_PRIVATE);
        final SharedPreferences sharprefs5= getSharedPreferences("ArchivoSP4", context.MODE_PRIVATE);

        final EditText Antecedentes = (EditText)findViewById(R.id.editText2);
        final EditText Medicacion = (EditText)findViewById(R.id.editText4);
        final EditText Nombre = (EditText)findViewById(R.id.editText);
        ImageButton Guardar = (ImageButton)findViewById(R.id.button2);
        final CheckBox Hombre = (CheckBox)findViewById(R.id.checkBox3);
        final CheckBox Mujer = (CheckBox)findViewById(R.id.checkBox4);



        SharedPreferences sharpref = getPreferences(context.MODE_PRIVATE);
        String valor = sharpref.getString("MiDato",null);
        Utilidades.Antecedentes=valor;
        Antecedentes.setText(valor);

        SharedPreferences sharpref2 = getPreferences(context.MODE_PRIVATE);
        String valor2 = sharpref2.getString("MiDato2",null);
        Utilidades.Medicacion= valor2;
        Medicacion.setText(valor2);



        SharedPreferences sharpref3 = getPreferences(context.MODE_PRIVATE);
        String valor3 = sharpref3.getString("MiDato3",null);
        Utilidades.Nombre=(valor3);
        Nombre.setText(valor3);

        SharedPreferences sharpref4 = getPreferences(context.MODE_PRIVATE);
        String valor4 = sharpref4.getString("MiDato4",null);
        Utilidades.Hombre=(String.valueOf(valor4));
        Hombre.setChecked(Boolean.parseBoolean(String.valueOf(valor4)));

        SharedPreferences sharpref5 = getPreferences(context.MODE_PRIVATE);
        String valor5 = sharpref5.getString("MiDato5",null);
        Utilidades.Mujer=(String.valueOf(valor5));
        Mujer.setChecked(Boolean.parseBoolean(String.valueOf(valor5)));


        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(Mujer.isChecked()==true&&Hombre.isChecked()==true){

                }else{
                SharedPreferences sharpref4 = getPreferences(context.MODE_PRIVATE);
                SharedPreferences.Editor editor4 = sharpref4.edit();
                editor4.putString("MiDato4", String.valueOf(Hombre.isChecked()));
                editor4.commit();

                SharedPreferences sharpref5 = getPreferences(context.MODE_PRIVATE);
                SharedPreferences.Editor editor5 = sharpref5.edit();
                editor5.putString("MiDato5", String.valueOf(Mujer.isChecked()));
                editor5.commit();}



                if (Antecedentes.getText().toString()==null) {
                }else{
                SharedPreferences sharpref = getPreferences(context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharpref.edit();
                editor.putString("MiDato", Antecedentes.getText().toString());
                editor.commit();}

                if (Medicacion.getText().toString()==null) {
                }else{
                SharedPreferences sharpref2 = getPreferences(context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharpref2.edit();
                editor2.putString("MiDato2", Medicacion.getText().toString());
                editor2.commit();}

                if (Nombre.getText().toString()==null){

                }else{
                SharedPreferences sharpref3 = getPreferences(context.MODE_PRIVATE);
                SharedPreferences.Editor editor3 = sharpref3.edit();
                editor3.putString("MiDato3", Nombre.getText().toString());
                editor3.commit();}

                onBackPressed();
            }
        });





    }



}
