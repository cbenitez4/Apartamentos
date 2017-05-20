package com.example.carlos.constructora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;

public class Registrar extends AppCompatActivity {

    private EditText cajaapartamento,cajatamaño,cajaprecio;
    private RadioButton balconsi;
    private RadioButton balconno;
    private RadioButton sombrasi;
    private RadioButton sombrano;
    private String[] lis;
    private ArrayAdapter adapterlistado;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        cajaapartamento = (EditText) findViewById(R.id.txtnomenclatura);
        cajaprecio = (EditText) findViewById(R.id.txtprecio);
        cajatamaño = (EditText) findViewById(R.id.txttamaño);
        balconsi = (RadioButton) findViewById(R.id.bsi);
        balconno = (RadioButton) findViewById(R.id.bno);
        sombrasi = (RadioButton) findViewById(R.id.ssi);
        sombrano = (RadioButton) findViewById(R.id.sno);
        cajaapartamento.requestFocus();

        /*lis = this.getResources().getStringArray(R.array.listado);
        adapterlistado = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lis);
        combolistado.setAdapter(adapterlistado);
        */

    }

    public boolean validar(){
        if(cajatamaño.getText().toString().isEmpty()){
            cajatamaño.setError(this.getResources().getString(R.string.error_vacio));
            cajatamaño.requestFocus();
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
        String apartamento, tamaño, precio, balcon = "", sombra="";
        Apartamento p;
        if (validar()) {
            apartamento = cajaapartamento.getText().toString();
            tamaño = cajatamaño.getText().toString();
            precio = cajaprecio.getText().toString();

            //Verifico si es masculino o Femenino
            if (balconsi.isChecked()) balcon = getResources().getString(R.string.si);
            else balcon = getResources().getString(R.string.no);

            if (sombrasi.isChecked()) sombra = getResources().getString(R.string.si);
            else sombra = getResources().getString(R.string.no);


            p = new Apartamento(apartamento, tamaño, precio, balcon, sombra);
            p.registrar(getApplicationContext());

            new AlertDialog.Builder(this).setMessage("Apartamento Guardado Exitosamente!").setCancelable(true).show();
            limpiar();
        }
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
}
