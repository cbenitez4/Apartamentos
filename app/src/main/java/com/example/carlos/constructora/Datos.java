package com.example.carlos.constructora;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Carlos on 19/05/2017.
 */

public class Datos {

    public static ArrayList<Apartamento> traerApartamentos(Context contexto) {
        ArrayList<Apartamento> apartamentos= new ArrayList<>();

        //Declarar Variables
        SQLiteDatabase db;
        String sql, apartamento, tamaño, precio, balcon, sombra;
        Apartamento p;
        //Abrir conexción
        ApartamentoSQLiteOpenHelper aux = new ApartamentoSQLiteOpenHelper(contexto,"DBApartamentos",null,1);
        db = aux.getReadableDatabase();

        //Cursor
        sql ="select * from Apartamentos";
        Cursor c =db.rawQuery(sql,null);

        //Recorido del cursor
        if(c.moveToFirst()){
            do{
                apartamento = c.getString(0);
                tamaño=c.getString(1);
                precio=c.getString(2);
                balcon=c.getString(3);
                sombra=c.getString(4);
                p = new Apartamento (apartamento, tamaño, precio, balcon, sombra);
                apartamentos.add(p);
            } while (c.moveToNext());
        }
        //Cierro la base de datos y retorno apartamentos
        db.close();
        return apartamentos;
    }





    public static Apartamento buscarApartamento(Context contexto,String apar){
        //Declarar Variables
        SQLiteDatabase db;
        String sql, apartamento, tamaño, precio, balcon, sombra;
        Apartamento p=null;
        //Abrir conexción
        ApartamentoSQLiteOpenHelper aux = new ApartamentoSQLiteOpenHelper(contexto,"DBApartamentos",null,1);
        db = aux.getReadableDatabase();

        //Cursor
        sql ="select * from Apartamentos where apartamento ='"+apar+"'";
        Cursor c =db.rawQuery(sql,null);

        //Recorido del cursor
        if(c.moveToFirst()){
            apartamento = c.getString(0);
            tamaño=c.getString(1);
            precio=c.getString(2);
            balcon=c.getString(3);
            sombra=c.getString(4);
            p = new Apartamento (apartamento, tamaño, precio, balcon, sombra);
        }
        //Cierro la base de datos y retorno apartamentos
        db.close();
        return p;
    }

}