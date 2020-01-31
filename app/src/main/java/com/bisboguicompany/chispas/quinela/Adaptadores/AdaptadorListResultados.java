package com.bisboguicompany.chispas.quinela.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bisboguicompany.chispas.quinela.Models.jornadas;
import com.bisboguicompany.chispas.quinela.R;

import java.util.List;

/**
 * Created by rdms on 04/11/2019.
 */

public class AdaptadorListResultados extends BaseAdapter {



    LayoutInflater flater;
    List<jornadas> datos;
    Context context;



    public  AdaptadorListResultados(Context contextS, List<jornadas> objetos){

        this.context = contextS;
        this.datos = objetos;
        flater = (LayoutInflater.from(contextS));
    }



    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return datos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= flater.inflate(R.layout.item_list_resultados_jornadas, null);


        TextView colmenas = (TextView) convertView.findViewById(R.id.txtNombreJornadas);
        TextView estatus = (TextView) convertView.findViewById(R.id.txtEstatus);
        TextView chivi = (TextView) convertView.findViewById(R.id.txtChivita);

        jornadas datitos = datos.get(position);


        colmenas.setText(datitos.getNombre());
        estatus.setText("En esta jornada se juegan: " + String.valueOf(datitos.getPuntos()) + " pts Grande" );
        chivi.setText("En esta jornada se juegan: " + String.valueOf(datitos.getPuntoschivita())  + " pts Chivita" );
        return convertView;
    }



}
