package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorPartidosAdmin;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.AgregarResultados.AgregarResultadosActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.EquiposJornadas.EquiposJornadasContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarContract;
import com.bisboguicompany.chispas.quinela.Models.Apuesta;
import com.bisboguicompany.chispas.quinela.Models.Equipos;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListPartidosAdminFragment  extends DaggerFragment implements ListPartidosAdminContract.View {

    public static ListPartidosAdminActivity mactivity;

    @Inject
    ListPartidosAdminContract.Presenter mPresenter;

    int clavejornada;
    int estatus;
    ListView listpartis;
    AdaptadorPartidosAdmin adaptadorPartidos;
    Button btnclose;
    ProgressDialog ringProgressDialog;



    @Inject
    public ListPartidosAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity aa = getActivity();
        mactivity = (ListPartidosAdminActivity) getActivity();
        aa.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mactivity.onFragmentInteraction("Partidos de la jornada");
        View v=  inflater.inflate(R.layout.fragment_list_partidos_admin, container, false);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras == null) {
            clavejornada = 0;
            estatus      = 0;
        } else {
            clavejornada = extras.getInt("jornada");
            estatus = extras.getInt("estatus");
            mPresenter.obtenerPartidos(clavejornada);
        }

        listpartis  = v.findViewById(R.id.listpartidos);
        btnclose    = v.findViewById(R.id.btnCerrar);


        listpartis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Apuesta select = (Apuesta)listpartis.getAdapter().getItem(position);
                Log.d("seleccionqdo","----> " + select.getPartidos());
                if(select.getStatus() == 1){
                    Intent agregar =  new Intent(getActivity(), AgregarResultadosActivity.class);
                    agregar.putExtra("partido",select.getPartidos());
                    agregar.putExtra("local",select.getNombrelocal());
                    agregar.putExtra("visitante",select.getNombrevisitante());

                    startActivity(agregar);

                }else{
                    mensajeError("Ya tiene asignado un resultado el equipo.");
                }

            }
        });


        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (estatus == 1){
                    limpiarTablas();
                }else{

                    mensajeError("La quinela ya se encuentra cerrada.");
                }

            }
        });

        return  v;

    }


    public void limpiarTablas(){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("¿Esta seguro en cerrar la quinela?");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mPresenter.cerrar(String.valueOf(clavejornada));
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    public void ExitoPartidos(List<Partidos> listpartidos) {
        mPresenter.obtenrEquipos(listpartidos);
    }

    @Override
    public void ErrorPartidos(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void ExitoEquipos(List<Partidos> listpartidos, List<Equipos> listEquipos) {

        List<Apuesta> listadoApuestas =  new ArrayList<>();
        String url = "";
        String url2 = "";
        String nombre1 = "";
        String nombre2 = "";

        int contador = 0;
        for (int i = 0 ; i < listpartidos.size(); i++){

            Log.d("listpqrtidos","----> " + listpartidos.get(i).getPartidos());

            for (int j = 0 ; j  < listEquipos.size(); j++){

                if (listpartidos.get(i).getIdEquipoLocal() == listEquipos.get(j).getIdteam()){
                    contador = contador + 1;

                    url = listEquipos.get(j).getLogo();
                    nombre1 = listEquipos.get(j).getNombre();

                }else if (listpartidos.get(i).getIdEquipoVisitante() == listEquipos.get(j).getIdteam()){
                    contador = contador + 1;

                    url2 = listEquipos.get(j).getLogo();
                    nombre2 = listEquipos.get(j).getNombre();
                }

                if(contador == 2){
                    break;
                }
            }

            if(contador == 2){
                Apuesta apos  =  new Apuesta(0,
                        listpartidos.get(i).getIdjornada(),
                        listpartidos.get(i).getIdEquipoLocal(),
                        listpartidos.get(i).getIdEquipoVisitante(),
                        listpartidos.get(i).getFecha(),
                        listpartidos.get(i).getStatus(),
                        listpartidos.get(i).getPartidos(),
                        0,
                        url,
                        url2,
                        nombre1,
                        nombre2);


                listadoApuestas.add(apos);
                url  = "";
                url2 = "";
                nombre1 = "";
                nombre2 = "";
                contador = 0;
            }

        }

        adaptadorPartidos = new AdaptadorPartidosAdmin(getActivity(),listadoApuestas);
        adaptadorPartidos.notifyDataSetChanged();
        listpartis.setAdapter(adaptadorPartidos);

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



    @Override
    public void ErrorEquipos(String mensaje) {
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

    //metodo que muestra el mensaje el error si se produce alguno al enviar el usuario y contraseña
    public void mensajeError(String mensaje){
        AlertDialog malertDialog = null;
        AlertDialog.Builder mdialogBuilder = null;
        if (mdialogBuilder == null) {
            mdialogBuilder = new AlertDialog.Builder(getActivity());

            mdialogBuilder.setTitle("Partidos");
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
}
