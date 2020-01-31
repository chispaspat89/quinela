package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorPuntos;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.ListPartidosAdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.AgregarPuntos.AgregarPuntosActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginActivity;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;
import com.bisboguicompany.chispas.quinela.R;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PuntosFragment  extends DaggerFragment implements PuntosContract.View,View.OnClickListener, View.OnKeyListener{

    @Inject
    PuntosContract.Presenter mPresenter;
    FloatingActionButton btnFloat;
    TextView txtNombreUsuario,txtNombre,txtver;
    ListView listasJornadas;
    AdaptadorPuntos adapterJornadas;

    @Inject
    public PuntosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_puntos, container, false);
        final View hView    = PuntosActivity.navigationView.getHeaderView(0);

        txtNombreUsuario    = (TextView)hView.findViewById(R.id.txtNombreCompletePaciente);
        txtNombre           = (TextView)hView.findViewById(R.id.txtestado);
        listasJornadas      = v.findViewById(R.id.lista_tiendas);
        txtver              = (TextView)hView.findViewById(R.id.txtVersion);

        txtver.setText(R.string.versionapp);



        listasJornadas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                jornadas select = (jornadas)listasJornadas.getAdapter().getItem(position);

                Intent detalle = new Intent(getActivity(), AgregarPuntosActivity.class);
                detalle.putExtra("jornada",select.getIdjornada());
                detalle.putExtra("cantidad",select.getPuntos());
                detalle.putExtra("chiva",select.getPuntoschivita());
                startActivity(detalle);


            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
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
    public void ExitoJornadasPartidos(List<jornadas> listjornadas) {
        adapterJornadas = new AdaptadorPuntos(getActivity(),listjornadas);
        adapterJornadas.notifyDataSetChanged();
        listasJornadas.setAdapter(adapterJornadas);
    }

    @Override
    public void ErrorJornadasPartidos(String mensaje) {
            mensajeError(mensaje);
    }


    public void mensajeError(String mensaje){
        AlertDialog malertDialog = null;
        AlertDialog.Builder mdialogBuilder = null;
        if (mdialogBuilder == null) {
            mdialogBuilder = new AlertDialog.Builder(getActivity());

            mdialogBuilder.setTitle("Puntos x jornada");
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
