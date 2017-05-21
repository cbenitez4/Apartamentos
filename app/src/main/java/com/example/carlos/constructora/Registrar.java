package com.example.carlos.constructora;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Registrar extends AppCompatActivity {

    private EditText cajaapartamento,cajatamaño,cajaprecio,cajapiso;
    private RadioButton balconsi;
    private RadioButton balconno;
    private RadioButton sombrasi;
    private RadioButton sombrano;
    private Resources res;
    private String[] lis;
    private ArrayAdapter adapterlistado;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        cajaapartamento = (EditText) findViewById(R.id.txtnomenclatura);
        cajapiso = (EditText) findViewById(R.id.txtpiso);
        cajaprecio = (EditText) findViewById(R.id.txtprecio);
        cajatamaño = (EditText) findViewById(R.id.txttamaño);
        balconsi = (RadioButton) findViewById(R.id.bsi);
        balconno = (RadioButton) findViewById(R.id.bno);
        sombrasi = (RadioButton) findViewById(R.id.ssi);
        sombrano = (RadioButton) findViewById(R.id.sno);
        cajapiso.requestFocus();

    }

    public boolean validar(){
        if(cajatamaño.getText().toString().isEmpty()){
            cajatamaño.setError(this.getResources().getString(R.string.error_vacio));
            cajatamaño.requestFocus();
            return false;

        }if(cajapiso.getText().toString().isEmpty()){
            cajapiso.setError(this.getResources().getString(R.string.error_vacio));
            cajapiso.requestFocus();
            return false;

        }
        if(cajaprecio.getText().toString().isEmpty()){
            cajaprecio.setError(this.getResources().getString(R.string.error_vacio));
            cajaprecio.requestFocus();
            return false;

        }
        if(cajaapartamento.getText().toString().isEmpty()){
            cajaapartamento.setError(this.getResources().getString(R.string.error_vacio));
            cajaapartamento.requestFocus();
            return false;

        }

        return true;
    }


    public void registrar(View v) {
        String apartamento, piso, tamaño, precio, balcon = "", sombra="";
        Apartamento p;
        if (validar()) {
            if (validarCantidad()){
                if (validarApartamento()){
            apartamento = cajaapartamento.getText().toString();
            piso = cajapiso.getText().toString();
            tamaño = cajatamaño.getText().toString();
            precio = cajaprecio.getText().toString();

            //Verifico si es masculino o Femenino
            if (balconsi.isChecked()) balcon = getResources().getString(R.string.si);
            else balcon = getResources().getString(R.string.no);

            if (sombrasi.isChecked()) sombra = getResources().getString(R.string.si);
            else sombra = getResources().getString(R.string.no);


            p = new Apartamento(apartamento, piso, tamaño, precio, balcon, sombra);
            p.registrar(getApplicationContext());

            Toast t=Toast.makeText(getApplicationContext(),getString(R.string.mensaje1), Toast.LENGTH_SHORT);
            t.show();
            i = new Intent(Registrar.this,Principal.class);
            startActivity(i);
        }}}
    }

    private void limpiar() {
        cajaapartamento.setText("");
        cajatamaño.setText("");
        cajaprecio.setText("");
        sombrasi.setChecked(true);
        sombrano.setChecked(false);
        balconsi.setChecked(true);
        balconno.setChecked(false);
        cajaapartamento.requestFocus();
    }

    public boolean validarCantidad(){
        ArrayList<Apartamento> a=Datos.traerApartamentos(getApplicationContext());
        String piso=cajapiso.getText().toString();
        int cont=0;
        for (int i=0;i<a.size();i++){
            if (a.get(i).getApartamento().equals(piso))cont=cont+1;
        }
        if (cont>=3){
            Toast t=Toast.makeText(getApplicationContext(),getString(R.string.piso_error), Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        return true;
    }

    public boolean validarApartamento(){
        ArrayList<Apartamento> apartamentos= Datos.traerApartamentos(getApplicationContext());
        for (int i=0;i<apartamentos.size();i++){
            if (apartamentos.get(i).getApartamento().equalsIgnoreCase(cajaapartamento.getText().toString())){
                Toast t=Toast.makeText(getApplicationContext(),getString(R.string.apartamento_igual), Toast.LENGTH_SHORT);
                t.show();
                cajaapartamento.requestFocus();
                return false;
            }
        }
        return true;
    }


}
