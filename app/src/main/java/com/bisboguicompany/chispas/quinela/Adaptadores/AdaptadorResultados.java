package com.bisboguicompany.chispas.quinela.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bisboguicompany.chispas.quinela.Models.Equipos;
import com.bisboguicompany.chispas.quinela.Models.ResultJornada;
import com.bisboguicompany.chispas.quinela.R;

import java.util.List;

/**
 * Created by rdms on 24/10/2019.
 */

public class AdaptadorResultados extends BaseAdapter {



    LayoutInflater flater;
    List<ResultJornada> datos;
    Context context;



    public  AdaptadorResultados(Context contextS, List<ResultJornada> objetos){

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
        convertView= flater.inflate(R.layout.item_resultado, null);

        TextView colmenas = (TextView) convertView.findViewById(R.id.txtNombre);

        ResultJornada datitos = datos.get(position);

        colmenas.setText( datitos.getNombre());

        return convertView;
    }


}
