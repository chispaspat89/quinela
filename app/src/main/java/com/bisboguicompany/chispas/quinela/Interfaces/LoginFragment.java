package com.bisboguicompany.chispas.quinela.Interfaces;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Registrarse.RegistraseActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Ventas.VentasActivity;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


@ActivityScoped
public class LoginFragment  extends DaggerFragment implements LoginContract.View, View.OnClickListener, View.OnKeyListener {


    @Inject
    LoginContract.Presenter mPresenter;

    public static LoginActivity mactivity;


    Button btnRegistro,btnRecuperar, btnInicio;

    ProgressDialog ringProgressDialog;
    EditText editUsuario, editPassword;

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;



    @Inject
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Activity aa = getActivity();
        mactivity = (LoginActivity) getActivity();
        aa.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        View v = inflater.inflate(R.layout.fragment_login, container, false);
        btnRegistro = (Button) v.findViewById(R.id.btnRegistrarse);
        btnRecuperar = (Button)v.findViewById(R.id.btnRecuperar);
        btnInicio   = (Button)v.findViewById(R.id. btnIniciar);
        editUsuario = v.findViewById(R.id.editUser);
        editPassword = v.findViewById(R.id.password);

        btnRegistro.setOnClickListener(this);
        btnRecuperar.setOnClickListener(this);
        btnInicio.setOnClickListener(this);

        checkAndRequestPermissions(getActivity());

        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                hideKeyboard(view);
                return false;
            }
        });
        return v;
    }


    private  boolean checkAndRequestPermissions(Activity activity) {

        //se crea los int de las permisos

        int permissionWriteStorage = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);



        //se crea un list de permisos para generar una peticion de multiples permisos aqui se agregarian otro si fueran necesarios
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionWriteStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }


        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }



    public void hideKeyboard(View view) {
        //InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(pub.INPUT_METHOD_SERVICE);
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnRegistrarse:
                Intent registro = new Intent(getActivity(), RegistraseActivity.class);
                startActivity(registro);

                break;
            case R.id.btnRecuperar:

                Intent recu = new Intent(getActivity(), RecuperarActivity.class);
                startActivity(recu);
                break;

            case R.id.btnIniciar:
                //Intent iniciar = new Intent(getActivity(), AdminActivity.class);
                //startActivity(iniciar);

                String usuario = editUsuario.getText().toString();
                String password = editPassword.getText().toString();

                if(!usuario.equals("") && !password.equals("")){
                    mPresenter.initSesion(usuario,password,getActivity());
                }else{
                    mensajeError("Debe de ingresar el usuario y contraseña registrado.");
                }

                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showEmptyUserError(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void showloading() {
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
    public void cancelarLoading() {
        if(ringProgressDialog != null){

            ringProgressDialog.dismiss();
        }
    }

    @Override
    public void errorAutentificacion(String msj) {

    }

    @Override
    public void showUserList() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showPrincipal() {

    }

    @Override
    public void sesion(Usuario datos, String pass) {

    }

    @Override
    public void irUsuarios() {
        Intent iniciar = new Intent(getActivity(), PrincipalActivity.class);
        startActivity(iniciar);
    }

    @Override
    public void irVendedores() {
        Intent iniciar = new Intent(getActivity(), VentasActivity.class);
        startActivity(iniciar);
    }

    @Override
    public void irAdmin() {
        Intent iniciar = new Intent(getActivity(), AdminActivity.class);
        startActivity(iniciar);
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


    //metodo que muestra el mensaje el error si se produce alguno al enviar el usuario y contraseña
    public void mensajeError(String mensaje){
        AlertDialog malertDialog = null;
        AlertDialog.Builder mdialogBuilder = null;
        if (mdialogBuilder == null) {
            mdialogBuilder = new AlertDialog.Builder(getActivity());

            mdialogBuilder.setTitle("Inicio de sesión");
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
