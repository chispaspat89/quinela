package com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorResultados;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.EquiposJornadas.EquiposJornadasActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.EquiposJornadas.EquiposJornadasContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.DetalleResultados.DetalleResultadosActivity;
import com.bisboguicompany.chispas.quinela.Models.ResultJornada;
import com.bisboguicompany.chispas.quinela.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultadosJornadaFragment extends DaggerFragment implements ResultadosJornadaContract.View,View.OnClickListener {

    public static ResultadosJornadaActivity mactivity;

    @Inject
    ResultadosJornadaContract.Presenter mPresenter;
    int clavejornada;


    ProgressDialog ringProgressDialog;
    ListView listresultadosjornadas;
    AdaptadorResultados adaptador;


    @Inject
    public ResultadosJornadaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras == null) {
            clavejornada = 0;
        } else {
            clavejornada = extras.getInt("jornada");

            mPresenter.obtenerresultados(String.valueOf(clavejornada));
        }
        Activity aa = getActivity();
        mactivity = (ResultadosJornadaActivity) getActivity();
        aa.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mactivity.onFragmentInteraction("Apuestas realizadas");




        View v                  =  inflater.inflate(R.layout.fragment_resultados_jornada, container, false);
        listresultadosjornadas  = v.findViewById(R.id.listresultados);

        listresultadosjornadas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ResultJornada data = (ResultJornada)listresultadosjornadas.getAdapter().getItem(position);
                //JsonArray jsonElements = (JsonArray) new Gson().toJsonTree(data.getListBest());

                //Log.d("cantidad","--->" + jsonElements.size());
                //Log.d("informacion","---->" + jsonElements.toString());

                Intent pasar = new Intent(getActivity(), DetalleResultadosActivity.class);
                pasar.putExtra("listado",data.getListBest());
                startActivity(pasar);



            }
        });


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


    //metodo que muestra el mensaje el error si se produce alguno al enviar el usuario y contraseña
    public void mensajeError(String mensaje){
        AlertDialog malertDialog = null;
        AlertDialog.Builder mdialogBuilder = null;
        if (mdialogBuilder == null) {
            mdialogBuilder = new AlertDialog.Builder(getActivity());

            mdialogBuilder.setTitle("Resultados");
            mdialogBuilder.setMessage(mensaje);

            mdialogBuilder.setNegativeButton("Aceptar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

            if (malertDialog == null) {
                malertDialog = mdialogBuilder.create();
                malertDialog.show();
            }

        }

    }

    @Override
    public void ExitoResultados(List<ResultJornada> listado) {
        adaptador = new AdaptadorResultados(getActivity(), listado);
        adaptador.notifyDataSetChanged();
        listresultadosjornadas.setAdapter(adaptador);

    }

    @Override
    public void ErrorResultados(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void mensajecargando() {
        ringProgressDialog = ProgressDialog.show(getActivity(), "", "Espere un momento ...", true);
        ringProgressDialog.setCancelable(true);
        ringProgressDialog.setCanceledOnTouchOutside(false);
        ringProgressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_BACK && !event.isCanceled()) {
                    if(ringProgressDialog.isShowing()) {
                        //your logic here for back button pressed event
                    }
                    return true;
                }
                return false;
            }
        });

        ringProgressDialog.onStart();
    }

    @Override
    public void cancelarmensaje() {
        if(ringProgressDialog != null){
            ringProgressDialog.dismiss();
        }
    }
}
