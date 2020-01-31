package com.bisboguicompany.chispas.quinela.Interfaces.Administrador;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorAdminJornadas;
import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorListJornadas;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.ListPartidosAdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Ventas.VentasContract;
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
public class AdmFragment extends DaggerFragment implements AdminContract.View,View.OnClickListener, View.OnKeyListener{



    @Inject
    AdminContract.Presenter mPresenter;
    FloatingActionButton btnFloat;
    TextView txtNombreUsuario,txtNombre,txtver;
    ListView listasJornadas;
   // AdaptadorListJornadas adapterJornadas;
   AdaptadorAdminJornadas adapterJornadas;
    @Inject
    public AdmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_adm, container, false);
        final View hView    = AdminActivity.navigationView.getHeaderView(0);

        txtNombreUsuario    = (TextView)hView.findViewById(R.id.txtNombreCompletePaciente);
        txtNombre           = (TextView)hView.findViewById(R.id.txtestado);
        txtver              = (TextView)hView.findViewById(R.id.txtVersion);

        txtver.setText(R.string.versionapp);

        btnFloat        = v.findViewById(R.id.floatingbtn);
        listasJornadas    = v.findViewById(R.id.lista_tiendas);

        btnFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crear = new Intent(getActivity(), CrearJornadaActivity.class);
                startActivity(crear);
            }
        });


        listasJornadas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                jornadas select = (jornadas)listasJornadas.getAdapter().getItem(position);

                Intent detalle = new Intent(getActivity(), ListPartidosAdminActivity.class);
                detalle.putExtra("jornada",select.getIdjornada());
                detalle.putExtra("estatus",select.getStatus());
                startActivity(detalle);


            }
        });
        return  v;
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
    public void ExitoJornadasPartidos(List<Partidos> partidos, List<jornadas> listjornadas) {
        adapterJornadas = new AdaptadorAdminJornadas(getActivity(),listjornadas);
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

            mdialogBuilder.setTitle("Jornadas");
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
