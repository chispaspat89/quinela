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
 * Created by rdms on 01/11/2019.
 */

public class AdaptadorResultadosList extends BaseAdapter {




    LayoutInflater flater;
    List<jornadas> datos;
    Context context;



    public  AdaptadorResultadosList(Context contextS, List<jornadas> objetos){

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
        convertView= flater.inflate(R.layout.item_list_results, null);


        TextView colmenas = (TextView) convertView.findViewById(R.id.txtNombreJornadas);

        jornadas datitos = datos.get(position);


        colmenas.setText(datitos.getNombre());




        return convertView;
    }


}
