package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.NuevoVendedor;


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

import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarContract;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NuevoVendedorFragment extends DaggerFragment implements NuevoVendedorContract.View,View.OnClickListener  {


    public static NuevoVendedorActivity mactivity;


    @Inject
    NuevoVendedorContract.Presenter mPresenter;

    Button btnRegistro;
    EditText txtnombre, txtnombreUsuario, txttelefono, txtcorreo, txtpass1,txtpass2;

    ProgressDialog ringProgressDialog;

    @Inject
    public NuevoVendedorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Activity aa = getActivity();
        mactivity = (NuevoVendedorActivity) getActivity();
        aa.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mactivity.onFragmentInteraction("Nuevo vendedor");

        View v= inflater.inflate(R.layout.fragment_nuevo_vendedor, container, false);
        btnRegistro = v.findViewById(R.id.btnRegistrar);
        txtnombre = v.findViewById(R.id.editnombre);
        txtnombreUsuario = v.findViewById(R.id.editUsuario);
        txttelefono = v.findViewById(R.id.edittel);
        txtcorreo = v.findViewById(R.id.editmail);
        txtpass1 = v.findViewById(R.id.editpass1);
        txtpass2 = v.findViewById(R.id.editpass2);



        btnRegistro.setOnClickListener(this);

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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistrar:

                String  name        = txtnombre.getText().toString();
                String nomUser      =  txtnombreUsuario.getText().toString();
                String tele         = txttelefono.getText().toString();
                String correoUser   =  txtcorreo.getText().toString();
                String pass1        = txtpass1.getText().toString();
                String pass2        =  txtpass2.getText().toString();


                if(name.equals("") || nomUser.equals("") || tele.equals("") || correoUser.equals("") || pass1.equals("") || pass2.equals("")){

                    mensajeError("Todos los campos son obligatorios.");
                }else{

                    int validar = 0;
                    int validarTele  = 0;
                    String mensaje = "";

                    if (isEmailValid(correoUser)){
                        validar = 1;
                    }else{
                        mensaje = mensaje +"El correo electrónico ingresado es incorrecto."+"\n";
                        validar = 0;
                    }


                    if (tele.length() >7 && tele.length() <=10){
                        validarTele = 1;
                    }else{
                        mensaje = mensaje + "El teléfono ingresado es incorrecto."+"\n";
                        validarTele = 0;
                    }


                    if (validar == 1 && validarTele == 1){

                        if (pass1.equals(pass2)){

                            Usuario objet =  new Usuario(0,
                                    name,
                                    "",
                                    "",
                                    nomUser,
                                    correoUser,
                                    "2",
                                    tele,
                                    pass1,
                                    "");

                            mPresenter.guardarInformacion(objet);

                        }else{
                            mensajeError("Las contraseñas ingresada no coinciden, por favor verifiquelas.");
                        }

                    }else{
                        mensajeError(mensaje);
                    }
                }

                break;
        }
    }



    public void mensajeError(String mensaje){
        AlertDialog malertDialog = null;
        AlertDialog.Builder mdialogBuilder = null;
        if (mdialogBuilder == null) {
            mdialogBuilder = new AlertDialog.Builder(getActivity());

            mdialogBuilder.setTitle("Registro vendedores");
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


    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    @Override
    public void exitoGuardado() {
        limpiarCampos();
        mensajeError("Se guardo con éxito la información, por favor inicie sesión con su usuario registrado.");
    }

    @Override
    public void ErrorGuardado(String mensaje) {
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


    public void limpiarCampos(){

        txtnombre.setText("");
        txtnombreUsuario.setText("");
        txttelefono.setText("");
        txtcorreo.setText("");
        txtpass1.setText("");
        txtpass1.setText("");

    }
}
