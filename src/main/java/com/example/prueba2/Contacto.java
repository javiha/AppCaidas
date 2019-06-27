package com.example.prueba2;


import android.widget.Button;
import android.widget.CheckBox;

public class Contacto {
    public  int id;
    public String nombre;
    public String telefono;
    public Boolean SMS;
    public Boolean Llamada;



    public Contacto() {

    }

    public Contacto(String telefono, String nombre, int id , Boolean SMS, Boolean Llamada) {

        this.telefono = telefono;
        this.nombre = nombre;
        this.id = id;
        this.SMS= SMS;
        this.Llamada= Llamada;

    }

    public Contacto(String nombre, String telefono, Boolean SMS, Boolean Llamada) {
        super();
        this.nombre = nombre;
        this.telefono = telefono;
        this.SMS = SMS;
        this.Llamada = Llamada;



    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean getSMS() {
        return SMS;
    }

    public Boolean getLlamada() { return Llamada;}



    public String getTelefono() {
        return telefono;
    }





    @Override
    public String toString() {
        return this.nombre;
    }
}
