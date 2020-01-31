package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.NuevaJornada;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminContract;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginActivity;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.R;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NuevaJornadaFragment extends DaggerFragment implements NuevaJornadaContract.View,View.OnClickListener, View.OnKeyListener {



    @Inject
    NuevaJornadaContract.Presenter mPresenter;

    TextView txtNombreUsuario,txtNombre,txtver;
    EditText editnombre,editnumero,editdescripcion;
    Button btnGuardar;

    ProgressDialog ringProgressDialog;



    @Inject
    public NuevaJornadaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nueva_jornada, container, false);
        final View hView    = AdminActivity.navigationView.getHeaderView(0);

        txtNombreUsuario    = (TextView)hView.findViewById(R.id.txtNombreCompletePaciente);
        txtNombre           = (TextView)hView.findViewById(R.id.txtestado);
        txtver              = (TextView)hView.findViewById(R.id.txtVersion);

        txtver.setText(R.string.versionapp);



        editnombre      = (EditText)v.findViewById(R.id.editNombre);
        editnumero      = (EditText)v.findViewById(R.id.editNumero);
        editdescripcion = (EditText)v.findViewById(R.id.editDescrip);

        btnGuardar = (Button)v.findViewById(R.id.btnIniciar);

        btnGuardar.setOnClickListener(this);


        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnIniciar:


                String nombre       = editnombre.getText().toString();
                String num          = editnumero.getText().toString();
                String descripcion  =  editdescripcion.getText().toString();

                if (nombre.equals("") || num.equals("")){

                    mensajeError("Los campos nombre y numero de jornada son obligatorios.");

                }else{

                    mPresenter.guardarJornada(nombre, num, descripcion);
                }



                break;
        }

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
    public void ErrorJornada(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void ExitoJornada(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void mensajeCargando() {
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
    public void cancelarMensaje() {
        if(ringProgressDialog != null){

            ringProgressDialog.dismiss();
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


    public void mensajeError(String mensaje){
        AlertDialog malertDialog = null;
        AlertDialog.Builder mdialogBuilder = null;
        if (mdialogBuilder == null) {
            mdialogBuilder = new AlertDialog.Builder(getActivity());

            mdialogBuilder.setTitle("Nueva jornada");
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
