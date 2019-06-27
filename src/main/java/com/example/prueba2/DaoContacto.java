package com.example.prueba2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoContacto extends SQLiteOpenHelper {
    String telefonos, ListaTelefonos, Ticks, SMS, SMS1;
    Boolean Llamadafinal = false;
    public static String DATABASE_NAME = "DBContactos";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_contacto = "contacto";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "nombre";
    private static final String KEY_PHONENUMBER = "telefono";
    private static final String KEY_SMS = "sms";
    private static final String KEY_LLAMADA = "llamada";
    public static String TAG = "tag";
    private static final String CREATE_TABLE_Contacto = "CREATE TABLE " + TABLE_contacto + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT," + KEY_PHONENUMBER + " TEXT," + KEY_SMS + " INTEGER," + KEY_LLAMADA + " INTEGER);";

    //"CREATE TABLE " +" KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT," + " KEY_NAME TEXT, " + " KEY_PHONEMUNBER TEXT, " + " KEY_SMS INTEGER)" ;

    private SQLiteDatabase db = null;



    public DaoContacto(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_Contacto);
    }

    public void open() {
        try { // Abrimos la base de datos en modo escritura
            db = this.getWritableDatabase();
        } catch (Exception e) {
            throw new RuntimeException("Error al abrir la base de datos.");
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS;" + CREATE_TABLE_Contacto);
        onCreate(db);
    }

    public long addContactoDetail(Contacto student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getNombre());
        values.put(KEY_PHONENUMBER, student.getTelefono());
        LlamadaTelefonos();
        Log.d("Final", String.valueOf(Llamadafinal));
        if (student.getSMS() == true) {
            values.put(KEY_SMS, 1);
        } else {
            values.put(KEY_SMS, 0);
        }
        if (Llamadafinal == false) {


            if (student.getLlamada() == true) {
              values.put(String.valueOf(KEY_LLAMADA), 1);
                } else {
                  values.put(String.valueOf(KEY_LLAMADA), 0);}
                  Llamadafinal=true;
         }else{
            values.put(String.valueOf(KEY_LLAMADA), 0);

            }



        long insert = db.insert(TABLE_contacto, null, values);
        return insert;
    }
    public void deleteEntry(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_contacto, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public int updateEntry(Contacto student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getNombre());
        values.put(KEY_PHONENUMBER, student.getTelefono());
        values.put(KEY_SMS,student.getSMS());
        values.put(KEY_LLAMADA,student.getLlamada());




        return db.update(TABLE_contacto, values, KEY_ID + " = ?",
                new String[]{String.valueOf(student.id)});
    }



    public List<Contacto> getAllStudentsList() {
        List<Contacto> studentsArrayList = new ArrayList<Contacto>();
        String selectQuery = "SELECT  * FROM " + TABLE_contacto;
        Log.d(TAG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {

                Contacto students = new Contacto();
                students.id = c.getInt(c.getColumnIndex(KEY_ID));
                students.telefono = c.getString(c.getColumnIndex(KEY_PHONENUMBER));
                students.nombre = c.getString(c.getColumnIndex(KEY_NAME));

                if (c.getInt(c.getColumnIndex(KEY_SMS))==0)
                {
                    students.SMS=false;
                }else{students.SMS=true;}
                if (c.getInt(c.getColumnIndex(KEY_LLAMADA))==0)
                {
                    students.Llamada=false;
                }else{students.Llamada=true;}



                studentsArrayList.add(students);
            } while (c.moveToNext());
        }

        return studentsArrayList;
    }

    public String Telefonos() {
        List<Contacto> studentsArrayList = new ArrayList<Contacto>();
        String selectQuery = "SELECT  * FROM " + TABLE_contacto;
        Log.d(TAG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Contacto students = new Contacto();
                students.telefono = c.getString(c.getColumnIndex(KEY_PHONENUMBER));
                if (c.getInt(c.getColumnIndex(KEY_LLAMADA))==0)
                {

                }else{telefonos= String.valueOf(students.telefono);}




            } while (c.moveToNext());

        }



        return telefonos;

    }

    public List SMS(){
        Log.d("SMS","prueba");
        List<Contacto> studentsArrayList = new ArrayList<Contacto>();
        String selectQuery = "SELECT  * FROM " + TABLE_contacto;
        Log.d(TAG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Contacto students = new Contacto();
                students.telefono = c.getString(c.getColumnIndex(KEY_PHONENUMBER));
                if (c.getInt(c.getColumnIndex(KEY_SMS))==0)
                {
                    SMS=null;
                    SMS1=null;

                }else{
                    if(SMS==null){
                        SMS= String.valueOf(students.telefono);
                        Utilidades.SMS=SMS;
                        Log.d("x",Utilidades.SMS);
                    }else{
                        if(SMS1==null){
                            SMS1= String.valueOf(students.telefono);
                            Utilidades.SMS1=SMS1;
                            Log.d("x",Utilidades.SMS1);

                        }
                    }




                }




            } while (c.moveToNext());

        }



        return Collections.singletonList(SMS + SMS1);

    }




    public String LlamadaTelefonos() {
        List<Contacto> studentsArrayList = new ArrayList<Contacto>();
        String selectQuery = "SELECT  * FROM " + TABLE_contacto;
        Log.d(TAG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        Llamadafinal=false;
        if (c.moveToFirst()) {
            do {
                Contacto students = new Contacto();

                if (c.getInt(c.getColumnIndex(KEY_LLAMADA)) == 0) {
                    Ticks = String.valueOf(c.getInt(c.getColumnIndex(KEY_SMS)));
                } else {
                    Ticks = String.valueOf(1);
                }


                if (Ticks == String.valueOf(1)){
                    Llamadafinal=true;
                }
            } while (c.moveToNext());

        }

        return Ticks;


    }




}

