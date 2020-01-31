package com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.DetalleResultados;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorDetalleResultados;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.ResultadosJornadaActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.ResultadosJornadaContract;
import com.bisboguicompany.chispas.quinela.Models.bets;
import com.bisboguicompany.chispas.quinela.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleResultadosFragment extends DaggerFragment implements DetalleResultadosContract.View,View.OnClickListener {

    public static DetalleResultadosActivity mactivity;



    @Inject
    DetalleResultadosContract.Presenter mPresenter;

    String datos;
    List<bets> listadobets =  new ArrayList<>();
    AdaptadorDetalleResultados adaptador;
    ListView listadoResultados;


    @Inject
    public DetalleResultadosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity aa = getActivity();
        mactivity = (DetalleResultadosActivity) getActivity();
        aa.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mactivity.onFragmentInteraction("Detalle de la apuesta");



        Bundle extras = getActivity().getIntent().getExtras();
        if (extras == null) {

            datos = "";
        } else {

            datos = extras.getString("listado");
            try {
                JSONArray json = new JSONArray(datos.toString());
                for(int i = 0 ; i < json.length(); i++){

                     JSONObject data =  new JSONObject(json.get(i).toString());

                     Log.d("data","");
                     /*bets infos = new bets (0,
                             data.getInt("idusuario"),
                             data.getInt("idpartido"),
                             data.getInt("credito"),
                             data.getInt("respuesta"),
                             data.getInt("idjornada"),
                             data.getInt("numero"),
                             data.getInt("idequipolocal"),
                             data.getString("nombrelocal"),
                             data.getString("logolocal"),
                             data.getInt("idequipovisitante"),
                             data.getString("nombrevisitante"),
                             data.getString("logovisitante"),
                             data.getString("fecha"),
                             data.getInt("status"),
                             data.getInt("resultadoLocal"),
                             data.getInt("resultadoVisitante"),
                             data.getInt("tie"),
                             data.getString("finalizado"));*/

                    //listadobets.add(infos);



                    int idusuario           = data.getInt("id_users");
                    int idpartido           = data.getInt("id_matches");
                    int idcredito           = data.getInt("id_credits");
                    int respuesta           = data.getInt("response");
                    int idjornada           = data.getInt("id_journeys");
                    int numero              = data.getInt("number");
                    int idequipolocal       = data.getInt("id_team_local");
                    String nombrelocal      = data.getString("name_local");
                    String logolocal        = data.getString("logo_local");
                    int idequipovisitante   = data.getInt("id_team_visiting");
                    String nombrevisitante  = data.getString("name_visiting");
                    String logovisitante    = data.getString("logo_visiting");
                    String fecha            = data.getString("match_date");
                    int status              = data.getInt("status");
                    int resultlocal         = data.getInt("result_local");
                    int resultvisitante     = data.getInt("result_visiting");
                    int tie                 = data.getInt("tie");
                    String mensa            = data.getString("final");


                    bets datobets = new bets(0,
                            idusuario,
                            idpartido,
                            idcredito,
                            respuesta,
                            idjornada,
                            numero,
                            idequipolocal,
                            nombrelocal,
                            logolocal,
                            idequipovisitante,
                            nombrevisitante,
                            logovisitante,
                            fecha,
                            status,
                            resultlocal,
                            resultvisitante,
                            tie,
                            mensa);


                    listadobets.add(datobets);
                }


                Log.d("datos","cantidad" + listadobets.size());


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        View v = inflater.inflate(R.layout.fragment_detalle_resultados, container, false);
        listadoResultados =  v.findViewById(R.id.listResultados);


        adaptador =  new AdaptadorDetalleResultados(getActivity(),listadobets);
        adaptador.notifyDataSetChanged();
        listadoResultados.setAdapter(adaptador);



        return v;
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onResume() {
        super.onResume();
        //Bind view to the presenter which will signal for the presenter to load the task.
        mPresenter.takeView(this);
    }

    @Override
    public void onPause() {
        mPresenter.dropView();
        super.onPause();
    }

}
