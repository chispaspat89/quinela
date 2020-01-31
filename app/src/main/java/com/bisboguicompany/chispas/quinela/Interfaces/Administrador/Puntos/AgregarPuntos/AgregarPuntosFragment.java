package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.AgregarPuntos;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaContract;
import com.bisboguicompany.chispas.quinela.R;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarPuntosFragment extends DaggerFragment implements AgregarPuntosContract.View{

    @Inject
    AgregarPuntosContract.Presenter mPresenter;
    public static AgregarPuntosActivity mactivity;


    Button btnGuardarGrande, btnguardarCHivitia;
    EditText editCantidad,editCantidadChivita;

    ProgressDialog ringProgressDialog;
    int idjornada = 0;
    int cantidad = 0;
    int cantidadchivita = 0;

    @Inject
    public AgregarPuntosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_agregar_puntos, container, false);
        Activity aa = getActivity();
        mactivity = (AgregarPuntosActivity) getActivity();
        aa.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mactivity.onFragmentInteraction("Agregar puntos");


        Bundle extras = getActivity().getIntent().getExtras();
        if (extras == null) {
            idjornada       = 0;
            cantidad        = 0 ;
            cantidadchivita = 0 ;
        } else {
            idjornada = extras.getInt("jornada");
            cantidad =  extras.getInt("cantidad");
            cantidadchivita = extras.getInt("chiva");
        }

        btnGuardarGrande          = v.findViewById(R.id.btngrande);
        btnguardarCHivitia  = v.findViewById(R.id.btnchivita);
        editCantidad = v.findViewById(R.id.editCantidadData);
        editCantidadChivita = v.findViewById(R.id.editCantidadDataChivita);


        editCantidad.setText(String.valueOf(cantidad));
        editCantidadChivita.setText(String.valueOf(cantidadchivita));

        btnGuardarGrande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cantidad = editCantidad.getText().toString();

                mPresenter.guardarCantidad(cantidad,String.valueOf(idjornada),String.valueOf(2));

            }
        });

        btnguardarCHivitia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String canChivita = editCantidadChivita.getText().toString();
                mPresenter.guardarCantidad(canChivita,String.valueOf(idjornada),String.valueOf(1));
            }
        });

        return v;
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
    public void ExtioGuardado(String mensaje) {

        mensajeError(mensaje);
    }

    @Override
    public void ErrorGuardado(String mensaje) {

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

    public void mensajeError(String mensaje){
        AlertDialog malertDialog = null;
        AlertDialog.Builder mdialogBuilder = null;
        if (mdialogBuilder == null) {
            mdialogBuilder = new AlertDialog.Builder(getActivity());

            mdialogBuilder.setTitle("Agregar puntos");
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
