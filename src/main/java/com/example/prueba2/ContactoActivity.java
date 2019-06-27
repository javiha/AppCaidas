package com.example.prueba2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.prueba2.DaoContacto;
import com.example.prueba2.Contacto;
import com.example.prueba2.R;
import com.example.prueba2.MainActivity;

public class ContactoActivity extends AppCompatActivity {

    EditText etNombreE, etID;
    EditText etTelefonoE;
    DaoContacto daocontacto;
    Contacto contacto;
    private CheckBox SMS,Llamada, Ubicacion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        SMS = (CheckBox) findViewById(R.id.checkBox);
        Llamada = (CheckBox) findViewById(R.id.checkBox2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        etNombreE = (EditText) findViewById(R.id.etNombreE);
        etTelefonoE = (EditText) findViewById(R.id.etTelefonoE);
        daocontacto = new DaoContacto(this);



        if (bundle != null) {

            contacto = new Contacto( bundle.get("phone").toString(), bundle.get("nombre").toString(), Integer.parseInt(bundle.get("id").toString()), Boolean.getBoolean(bundle.get("sms").toString()),  Boolean.getBoolean(bundle.get("llamada").toString())  );
            etNombreE.setText(contacto.getNombre());
            etTelefonoE.setText(contacto.getTelefono());
        }

        findViewById(R.id.checkBox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loguearCheckboxSMS();
            }
        });

        findViewById(R.id.checkBox2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loguearCheckboxLlamada();
            }
        });


    }

    public void loguearCheckboxSMS() {
        String s = "Estado SMS: " + (SMS.isChecked() ? "Marcado" : "No Marcado");
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void loguearCheckboxLlamada() {
        String s = "Estado Llamada: " + (Llamada.isChecked() ? "Marcado" : "No Marcado");
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
    public void loguearCheckboxUbicacion() {
        String s = "Estado Ubicacion: " + (Ubicacion.isChecked() ? "Marcado" : "No Marcado");
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }





    public void Atras(View v) {
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }

   /* public void Guardar(View v) {
        if (!etNombreE.getText().toString().equals("") && !etTelefonoE.getText().toString().equals("")) {


            daocontacto.updateEntry(new Contacto( etTelefonoE.getText().toString(), etNombreE.getText().toString(), contacto.getId(), SMS.isChecked()), Llamada.isChecked(), Ubicacion.isChecked());
            Intent i = new Intent(ContactoActivity.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(ContactoActivity.this,"Editado Correctamente",Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(ContactoActivity.this, "Ingreso los datos requeridos", Toast.LENGTH_SHORT).show();
        }

    }*/









}
