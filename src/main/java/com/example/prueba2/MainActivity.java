package com.example.prueba2;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.prueba2.AdapterContacto;
import com.example.prueba2.DaoContacto;
import com.example.prueba2.Contacto;
import com.example.prueba2.R;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission_group.SMS;



public class MainActivity extends AppCompatActivity {

    EditText etNombre, etTelefono;
    Button btnRegistrar;
    CheckBox etSMS,etLlamada,etUbicacion;
    DaoContacto daocontacto;
    List<Contacto> contactos;
    ListView listViewContactos;
    String telefonos, SMS;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        daocontacto = new DaoContacto(this);
        contactos = new ArrayList<>();
        listViewContactos = (ListView) findViewById(R.id.listViewContactos);
       // listViewContactos.setItemsCanFocus(false);
        //listViewContactos.setClickable(true);
        listViewContactos.setLongClickable(true);
        Listar();
        Prueba2();







        listViewContactos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int pos, long arg5) {
                Log.d("Salir","Salir");
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Eliminar")
                        .setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Contacto contacto = (Contacto) listViewContactos.getItemAtPosition(pos);
                                daocontacto.deleteEntry(contacto.id);
                                Listar();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(R.drawable.alert)
                        .show();
                return true;
            }
        });
















        listViewContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Entrar","Entrar");
                Contacto contacto = (Contacto) listViewContactos.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this, ContactoActivity.class);
                i.putExtra("id", contacto.getId());
                i.putExtra("nombre", contacto.getNombre());
                i.putExtra("phone", contacto.getTelefono());
                i.putExtra("SMS",contacto.getSMS());
                i.putExtra("Llamada", contacto.getLlamada());
                startActivity(i);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final LayoutInflater inflater = getLayoutInflater();
                final View dialoglayout = inflater.inflate(R.layout.dialog, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialoglayout);
                etNombre = (EditText) dialoglayout.findViewById(R.id.etNombre);
                etTelefono = (EditText) dialoglayout.findViewById(R.id.etTelefono);
                btnRegistrar = (Button) dialoglayout.findViewById(R.id.btnRegistrar);
                etSMS = (CheckBox) dialoglayout.findViewById(R.id.SMSbox);
                etLlamada = (CheckBox) dialoglayout.findViewById(R.id.LlamadaBox);
                btnRegistrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!etNombre.getText().toString().equals("") && !etTelefono.getText().toString().equals("")) {


                            Contacto contacto = new Contacto(etNombre.getText().toString(), etTelefono.getText().toString(),etSMS.isChecked(),etLlamada.isChecked());
                            daocontacto.addContactoDetail(contacto);
                            Listar();
                            Toast.makeText(MainActivity.this, "Registrado Correctamente "  , Toast.LENGTH_SHORT).show();



                        } else {

                            Toast.makeText(MainActivity.this, "Ingrese los datos requeridos"  , Toast.LENGTH_SHORT).show();
                        }
                    }


                });
                builder.show();
            }
        });
    }



   private void Listar() {

        contactos = daocontacto.getAllStudentsList();
        AdapterContacto adapterMovimiento = new AdapterContacto(MainActivity.this, contactos);
        listViewContactos.setAdapter(adapterMovimiento);



    }





    public void Atras2(View v) {
        Intent atras2 = new Intent(this, AppCaidas.class);
        startActivity(atras2);

    }

    public void Prueba2(){
        telefonos = String.valueOf(daocontacto.Telefonos());
        SMS =String.valueOf(daocontacto.SMS());
        Utilidades.Telefono=telefonos;
        Log.d("x",SMS);


    }
    public void Borrar(View v) {
        listViewContactos = (ListView) findViewById(R.id.listViewContactos);
        int position = listViewContactos.getPositionForView(v);
        Contacto contacto = (Contacto) listViewContactos.getItemAtPosition(position);
        daocontacto.deleteEntry(contacto.id);
        Listar();
    }



    @Override
    public void onBackPressed() {

        Intent atras2 = new Intent(this, AppCaidas.class);
        startActivity(atras2);
    }
}
