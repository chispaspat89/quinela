package com.bisboguicompany.chispas.quinela.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bisboguicompany.chispas.quinela.DATA.ConstanteServidor;
import com.bisboguicompany.chispas.quinela.Models.ResultJornada;
import com.bisboguicompany.chispas.quinela.Models.bets;
import com.bisboguicompany.chispas.quinela.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rdms on 24/10/2019.
 */

public class AdaptadorDetalleResultados extends BaseAdapter {


    LayoutInflater flater;
    List<bets> datos;
    Context context;



    public  AdaptadorDetalleResultados(Context contextS, List<bets> objetos){

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
        convertView= flater.inflate(R.layout.item_bets, null);

        ImageView img1      = (ImageView) convertView.findViewById(R.id.imageView12);
        TextView nombre1    = (TextView) convertView.findViewById(R.id.txtlocal);
        TextView cantLocal       = (TextView) convertView.findViewById(R.id.txtcantlocal);


        ImageView img2       = (ImageView) convertView.findViewById(R.id.imageView13);
        TextView nombre2     = (TextView) convertView.findViewById(R.id.txtvisitante);
        TextView gol2        = (TextView) convertView.findViewById(R.id.txt2);

        TextView estatus     = (TextView) convertView.findViewById(R.id.txtaciertos);

        bets datitos = datos.get(position);


        nombre1.setText(datitos.getNombrelocal());
        nombre2.setText(datitos.getNombrevisitante());

        cantLocal.setText(String.valueOf(datitos.getResultadoLocal()));
        gol2.setText(String.valueOf(datitos.getResultadoVisitante()));

        Picasso.get().load(ConstanteServidor.server+datitos.getLogolocal()).into(img1);
        Picasso.get().load(ConstanteServidor.server+datitos.getLogovisitante()).into(img2);

        estatus.setText(datitos.getFinalizado());



        return convertView;
    }

}
