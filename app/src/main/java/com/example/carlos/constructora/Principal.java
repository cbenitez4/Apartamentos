package com.example.carlos.constructora;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    private ListView opciones;
    private String[] opc;
    private ArrayAdapter adapter;
    private Intent i;
    private Resources res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        res=this.getResources();
        opciones =(ListView)findViewById(R.id.lstopciones);
        opc = getResources().getStringArray(R.array.opciones);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,opc);
        opciones.setAdapter(adapter);
        opciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        i = new Intent(Principal.this,Registrar.class);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(Principal.this,Lista_registrados.class);
                        startActivity(i);
                        break;

                    case 2:
                        new AlertDialog.Builder(Principal.this).setMessage(tienenbalconysombra()).setCancelable(true).show();
                        break;
                    case 3:
                        new AlertDialog.Builder(Principal.this).setMessage(masCaro()).setCancelable(true).show();
                        break;
                    case 4:
                        new AlertDialog.Builder(Principal.this).setMessage(mayorTamaño()).setCancelable(true).show();
                        break;
                    case 5:
                        new AlertDialog.Builder(Principal.this).setMessage(promedio()).setCancelable(true).show();
                        break;
                }
            }

            });









    }
    public String tienenbalconysombra() {
        ArrayList<Apartamento> a=Datos.traerApartamentos(getApplicationContext());
        String mensaje,sombra,balcon,b,s;
        int contb=0,conts=0,cont=0;
        for (int i=0;i<a.size();i++){
            b=res.getString(R.string.si);
            s=res.getString(R.string.si);
            if (a.get(i).getBalcon().equals(b)){
                contb=contb+1;
            }
            if (a.get(i).getSombra().equals(s)){
                conts=conts+1;
            }
            if (a.get(i).getBalcon().equals(b) && a.get(i).getSombra().equals(s)){
                cont=cont+1;
            }
        }
        balcon=res.getString(R.string.bsi)+" "+String.valueOf(contb);
        sombra=res.getString(R.string.ssi)+" "+String.valueOf(conts);

        mensaje=balcon+"\n"+sombra+"\n"+res.getString(R.string.byssi)+" "+String.valueOf(cont);
        return mensaje;
    }

    public String masCaro(){
        String mensaje,piso;
        int precio1,precio2;
        ArrayList<Apartamento> a=Datos.traerApartamentos(getApplicationContext());
        precio1=Integer.parseInt(a.get(0).getPrecio());
        piso=a.get(0).getApartamento();
        for (int i=1;i<a.size();i++){
            precio2=Integer.parseInt(a.get(i).getPrecio());
            if (precio1<precio2){
                piso=a.get(i).getApartamento();
            }
        }
        mensaje=res.getString(R.string.mascaro)+" = "+getString(R.string.piso)+" "+piso;
        return mensaje;
    }

    public String mayorTamaño(){
        String mensaje,nom;
        int metros,metros2;
        ArrayList<Apartamento> a=Datos.traerApartamentos(getApplicationContext());
        metros=Integer.parseInt(a.get(0).getTamaño());
        nom=a.get(0).getPiso();
        for (int i=1;i<a.size();i++){
            metros2=Integer.parseInt(a.get(i).getTamaño());
            if (metros<metros2){
                metros=metros2;
                nom=a.get(i).getPiso();
            }
        }
        mensaje=res.getString(R.string.nomenclatura)+": "+nom+"\n"
                +res.getString(R.string.tamaño)+": "+String.valueOf(metros)+" M2";
        return mensaje;
    }

    public String promedio(){
        String mensaje;
        int metros=0,promedio;
        ArrayList<Apartamento> a=Datos.traerApartamentos(getApplicationContext());
        for (int i=0;i<a.size();i++){
            metros=metros+Integer.parseInt(a.get(i).getTamaño());
        }
        promedio=metros/a.size();
        mensaje=res.getString(R.string.promedio)+": "+String.valueOf(promedio);
        return mensaje;
    }


}
