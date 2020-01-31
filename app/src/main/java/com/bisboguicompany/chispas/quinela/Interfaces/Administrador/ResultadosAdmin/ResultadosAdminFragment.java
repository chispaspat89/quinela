package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorListJornadas;
import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorResultados;
import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorResultadosList;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.ListPartidosAdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.Ganadores.GanadoresActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginActivity;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;
import com.bisboguicompany.chispas.quinela.R;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultadosAdminFragment extends DaggerFragment implements ResultadosAdminContract.View,View.OnClickListener {


    public static ResultadosAdminActivity mactivity;

    @Inject
    ResultadosAdminContract.Presenter mPresenter;

    TextView txtNombreUsuario,txtNombre,txtver;
    ListView listasJornadas;
    //AdaptadorListJornadas adapterJornadas;
    AdaptadorResultadosList adapterJornadas;

    FloatingActionButton btnFloat;
    ProgressDialog ringProgressDialog;



    @Inject
    public ResultadosAdminFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Activity aa = getActivity();
        mactivity = (ResultadosAdminActivity) getActivity();
        aa.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        View v =  inflater.inflate(R.layout.fragment_resultados_admin, container, false);


        final View hView    = ResultadosAdminActivity.navigationView.getHeaderView(0);

        txtNombreUsuario    = (TextView)hView.findViewById(R.id.txtNombreCompletePaciente);
        txtNombre           = (TextView)hView.findViewById(R.id.txtestado);
        txtver              = (TextView)hView.findViewById(R.id.txtVersion);

        txtver.setText(R.string.versionapp);


        listasJornadas      = v.findViewById(R.id.listJornadas);
        btnFloat            = v.findViewById(R.id.floatingbtn);


        listasJornadas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                jornadas nuevo =  (jornadas)listasJornadas.getAdapter().getItem(position);

                Intent gana =  new Intent(getActivity(), GanadoresActivity.class);
                gana.putExtra("jornada",nuevo.getIdjornada());
                startActivity(gana);
            }
        });


        btnFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //limpiar la base de datos
                limpiarTablas();
            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void cerrar() {


        Intent datos =  new Intent(getActivity(), LoginActivity.class);
        datos.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(datos);
        getActivity().finish();
    }

    @Override
    public void ExitoUsuario(Usuario datos) {
        txtNombreUsuario.setText(datos.getNombreUsuario());
        txtNombre.setText(datos.getNombre());
    }

    @Override
    public void ExitoJornadasPartidos(List<Partidos> partidos, List<jornadas> listjornadas) {
        adapterJornadas = new AdaptadorResultadosList(getActivity(),listjornadas);
        adapterJornadas.notifyDataSetChanged();
        listasJornadas.setAdapter(adapterJornadas);
    }


    @Override
    public void ErrorJornadasPartidos(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void ExitoReinicio(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void ErrorReinicio(String mensaje) {
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
    public void cancelarcargando() {
        if(ringProgressDialog != null){
            ringProgressDialog.dismiss();
        }
    }


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


    public void limpiarTablas(){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("¿Esta seguro en eliminar la información generada en la base de datos de la aplicación de GrandeMX?");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mPresenter.reiniciar();
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
