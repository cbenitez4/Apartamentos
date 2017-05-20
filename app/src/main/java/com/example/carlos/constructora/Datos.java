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
        String sql, apartamento, piso, tamaño, precio, balcon, sombra;
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
                piso = c.getString(0);
                apartamento = c.getString(1);
                tamaño=c.getString(2);
                precio=c.getString(3);
                balcon=c.getString(4);
                sombra=c.getString(5);
                p = new Apartamento (piso, apartamento, tamaño, precio, balcon, sombra);
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
        String sql, apartamento, piso, tamaño, precio, balcon, sombra;
        Apartamento p=null;
        //Abrir conexción
        ApartamentoSQLiteOpenHelper aux = new ApartamentoSQLiteOpenHelper(contexto,"DBApartamentos",null,1);
        db = aux.getReadableDatabase();

        //Cursor
        sql ="select * from Apartamentos where apartamento ='"+apar+"'";
        Cursor c =db.rawQuery(sql,null);

        //Recorido del cursor
        if(c.moveToFirst()){
            piso = c.getString(0);
            apartamento = c.getString(1);
            tamaño=c.getString(2);
            precio=c.getString(3);
            balcon=c.getString(4);
            sombra=c.getString(5);
            p = new Apartamento (piso, apartamento, tamaño, precio, balcon, sombra);
        }
        //Cierro la base de datos y retorno apartamentos
        db.close();
        return p;
    }

}