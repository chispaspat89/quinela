package com.bisboguicompany.chispas.quinela.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bisboguicompany.chispas.quinela.Models.Apuesta;
import com.bisboguicompany.chispas.quinela.Models.jornadas;
import com.bisboguicompany.chispas.quinela.R;

import java.util.List;

public class AdaptadorPartidosAdmin extends BaseAdapter {


    LayoutInflater flater;
    List<Apuesta> datos;
    Context context;



    public  AdaptadorPartidosAdmin(Context contextS, List<Apuesta> objetos){

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
        convertView= flater.inflate(R.layout.item_partidos_admin, null);


        TextView local = (TextView) convertView.findViewById(R.id.txtLocal);
        TextView visitante = (TextView) convertView.findViewById(R.id.txtVisitante);
        TextView contra = (TextView) convertView.findViewById(R.id.txtcontra);

        Apuesta datitos = datos.get(position);



        local.setText(datitos.getNombrelocal());
        visitante.setText(datitos.getNombrevisitante());
        contra.setText("VS");


        return convertView;
    }



}
