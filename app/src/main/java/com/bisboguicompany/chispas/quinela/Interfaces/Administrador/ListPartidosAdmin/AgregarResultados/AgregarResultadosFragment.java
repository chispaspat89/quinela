package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.AgregarResultados;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.ListPartidosAdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.ListPartidosAdminContract;
import com.bisboguicompany.chispas.quinela.R;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class AgregarResultadosFragment extends DaggerFragment implements AgregarResultadoContract.View {

    public static AgregarResultadosActivity mactivity;

    @Inject
    AgregarResultadoContract.Presenter mPresenter;

    int idpartido;

    TextView txtlocal,txtvisitante;
    String nombreLocal, nombreVisitante;
    ProgressDialog ringProgressDialog;
    EditText editlocal,editvisitante;

    Button btng;

    @Inject
    public AgregarResultadosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Activity aa = getActivity();
        mactivity = (AgregarResultadosActivity) getActivity();
        aa.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mactivity.onFragmentInteraction("Agregar resultado");
        View v =  inflater.inflate(R.layout.fragment_agregar_resultados, container, false);
        txtlocal = v.findViewById(R.id.txtLocal);
        txtvisitante = v.findViewById(R.id.txtVisitante);
        editlocal = v.findViewById(R.id.editLocal);
        editvisitante = v.findViewById(R.id.editvisit);
        btng = v.findViewById(R.id.btnGUardar);

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras == null) {
            idpartido = 0;
        } else {
            idpartido = extras.getInt("partido");
            nombreLocal = extras.getString("local");
            nombreVisitante = extras.getString("visitante");

            txtlocal.setText(nombreLocal);
            txtvisitante.setText(nombreVisitante);
        }


        btng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dato1 = editlocal.getText().toString();
                String dato2 = editvisitante.getText().toString();

                if(dato1.equals("") || dato2.equals("")){
                    mensajeError("Debe de ingresar como quedo el marcador del partido.");
                }else{

                    JSONObject finalObjeto = new JSONObject();
                    try {
                        finalObjeto.put("id",idpartido);
                        finalObjeto.put("result_local",dato1);
                        finalObjeto.put("result_visiting",dato2);

                        mPresenter.guardarResultado(finalObjeto);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
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
    public void ExitoGuardado(String mensaje) {
        btng.setVisibility(View.GONE);
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
    public void cancelarcartando() {
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
}
