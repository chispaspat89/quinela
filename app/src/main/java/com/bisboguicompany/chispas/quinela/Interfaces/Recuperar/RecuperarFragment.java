package com.bisboguicompany.chispas.quinela.Interfaces.Recuperar;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Registrarse.RegistraseActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Registrarse.RegistraseContract;
import com.bisboguicompany.chispas.quinela.R;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */



@ActivityScoped
public class RecuperarFragment  extends DaggerFragment implements RecuperarContract.View{


    public static RecuperarActivity mactivity;
    ProgressDialog ringProgressDialog;

    @Inject
    RecuperarContract.Presenter mPresenter;
    EditText datosUsuario;
    Button btnRegistrar;


    @Inject
    public RecuperarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Activity aa = getActivity();
        mactivity = (RecuperarActivity) getActivity();
        aa.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mactivity.onFragmentInteraction("Recuperar contraseña");


        View v= inflater.inflate(R.layout.fragment_recuperar, container, false);
        datosUsuario = v.findViewById(R.id.editRecuperar);
        btnRegistrar = v.findViewById(R.id.btnRegistrar);




        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String informacion = datosUsuario.getText().toString();

                if(informacion.equals("")){

                    mensajeError("Debe de ingresar el nombre de usuario que registro.");
                }else{

                    mPresenter.recuperarPass(informacion);
                }
            }
        });

        return v;
    }

    //metodo que muestra el mensaje el error si se produce alguno al enviar el usuario y contraseña
    public void mensajeError(String mensaje){
        AlertDialog malertDialog = null;
        AlertDialog.Builder mdialogBuilder = null;
        if (mdialogBuilder == null) {
            mdialogBuilder = new AlertDialog.Builder(getActivity());

            mdialogBuilder.setTitle("Recuperar contraseña");
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

    @Override
    public void ExitoRecuperar(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void ErrorRecuperar(String mensaje) {
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
