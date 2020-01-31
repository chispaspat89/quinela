package com.bisboguicompany.chispas.quinela.Interfaces.EditarPerfil;

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

import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Registrarse.RegistraseActivity;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class EditarPerfilFragment extends DaggerFragment implements EditarPerfilContract.View,View.OnClickListener {

    public static EditarPerfilActivity mactivity;


    @Inject
    EditarPerfilContract.Presenter mPresenter;

    Button btnRegistro;
    EditText txtnombre, txtnombreUsuario, txttelefono, txtcorreo, txtpass1,txtpass2;
    Usuario datosUsuario;

    ProgressDialog ringProgressDialog;
    TextView txtNombreUsuario,txtNombre;


    @Inject
    public EditarPerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Activity aa = getActivity();
        mactivity = (EditarPerfilActivity) getActivity();
        aa.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);




        View v = inflater.inflate(R.layout.fragment_editar_perfil, container, false);
        final View hView    = EditarPerfilActivity.navigationView.getHeaderView(0);

        txtNombreUsuario    = (TextView)hView.findViewById(R.id.txtNombreCompletePaciente);
        txtNombre           = (TextView)hView.findViewById(R.id.txtestado);




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
    public void datosUsuario(Usuario datos) {
        datosUsuario = datos;

        txtNombreUsuario.setText(datos.getNombreUsuario());
        txtNombre.setText(datos.getNombre());

        txtnombre.setText(datos.getNombre());
        txtnombreUsuario.setText(datos.getNombreUsuario());
        txttelefono.setText(datos.getTelefono());
        txtcorreo.setText(datos.getCorreo());
    }

    @Override
    public void exitoActualizacion() {
        mensajeError("La información se actulizo con éxito");
    }

    @Override
    public void ErrorActualizacion(String mensaje) {

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
    public void cancelarmensaje() {
        if(ringProgressDialog != null){

            ringProgressDialog.dismiss();
        }
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


                if(name.equals("") || nomUser.equals("") || tele.equals("") || correoUser.equals("")){

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

                        if (!pass1.equals("") || !pass2.equals("")){

                            if (pass1.equals(pass2)){

                                Usuario objet =  new Usuario(0,
                                        name,
                                        "",
                                        "",
                                        nomUser,
                                        correoUser,
                                        "3",
                                        tele,
                                        pass1,
                                        datosUsuario.getUsuarioId());

                                mPresenter.actualizarPerfil(objet);

                            }else{
                                mensajeError("Las contraseñas ingresada no coinciden, por favor verifiquelas.");
                            }
                        }else{

                            Usuario objet =  new Usuario(0,
                                    name,
                                    "",
                                    "",
                                    nomUser,
                                    correoUser,
                                    "3",
                                    tele,
                                    "",
                                    datosUsuario.getUsuarioId());
                            mPresenter.actualizarPerfil(objet);
                        }



                    }else{
                        mensajeError(mensaje);
                    }
                }

                break;
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



    public void mensajeError(String mensaje){
        AlertDialog malertDialog = null;
        AlertDialog.Builder mdialogBuilder = null;
        if (mdialogBuilder == null) {
            mdialogBuilder = new AlertDialog.Builder(getActivity());

            mdialogBuilder.setTitle("Editar perfil");
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
