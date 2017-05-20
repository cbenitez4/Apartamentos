package com.example.carlos.constructora;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Carlos on 19/05/2017.
 */

public class Apartamento {
    private String piso;
    private String apartamento;
    private String tamaño;
    private String precio;
    private String balcon;
    private String sombra;

    public Apartamento(String piso, String apartamento, String tamaño, String precio, String balcon, String sombra) {
        this.piso = piso;
        this.apartamento = apartamento;
        this.tamaño = tamaño;
        this.precio = precio;
        this.balcon = balcon;
        this.sombra = sombra;

    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }


    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getBalcon() {
        return balcon;
    }

    public void setBalcon(String balcon) {
        this.balcon = balcon;
    }

    public String getSombra() {
        return sombra;
    }

    public void setSombra(String sombra) {
        this.sombra = sombra;
    }

    public void registrar (Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexión de base de datos en modo escritura
        ApartamentoSQLiteOpenHelper aux =new ApartamentoSQLiteOpenHelper(contexto,"DBApartamentos",null,1);
        db = aux.getWritableDatabase();


        //insertar
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("piso",this.getPiso());
        nuevoRegistro.put("apartamento",this.getApartamento());
        nuevoRegistro.put("tamaño",this.getTamaño());
        nuevoRegistro.put("precio",this.getPrecio());
        nuevoRegistro.put("balcon",this.getBalcon());
        nuevoRegistro.put("sombra",this.getSombra());
        //Inserto en la base de datos
        db.insert("Apartamentos",null, nuevoRegistro);
        //cierro la conexión
        db.close();
    }

}
