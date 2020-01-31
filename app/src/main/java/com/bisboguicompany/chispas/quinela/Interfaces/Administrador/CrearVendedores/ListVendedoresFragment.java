package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorVendedor;
import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorVentas;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.NuevoVendedor.NuevoVendedorActivity;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
/**
 * A simple {@link Fragment} subclass.
 */
public class ListVendedoresFragment extends DaggerFragment implements ListVendedoresContract.View{

    public static ListVendedoresActivity mactivity;
    FloatingActionButton btnFloat;


    @Inject
    ListVendedoresContract.Presenter mPresenter;


    ListView listadoUsuarios;
    AdaptadorVendedor adaptadorUsuarios;
    List<Usuario> listGeneral = new ArrayList<>();
    Usuario usuarioSelecionado;
    TextView txtNombreUsuario,txtNombre,txtver;

    ProgressDialog ringProgressDialog;



    @Inject
    public ListVendedoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Activity aa = getActivity();
        mactivity = (ListVendedoresActivity) getActivity();
        aa.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        View v = inflater.inflate(R.layout.fragment_list_vendedores, container, false);
        final View hView    = ListVendedoresActivity.navigationView.getHeaderView(0);

        txtNombreUsuario    = (TextView)hView.findViewById(R.id.txtNombreCompletePaciente);
        txtNombre           = (TextView)hView.findViewById(R.id.txtestado);

        txtver              = (TextView)hView.findViewById(R.id.txtVersion);

        txtver.setText(R.string.versionapp);


        btnFloat = v.findViewById(R.id.floatingbtn);

        listadoUsuarios = v.findViewById(R.id.lista_vendedores);
        setHasOptionsMenu(true);



        listadoUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                usuarioSelecionado = (Usuario) listadoUsuarios.getAdapter().getItem(position);
                mostrarMensaje();
            }
        });



        btnFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crear = new Intent(getActivity(), NuevoVendedorActivity.class);
                startActivity(crear);
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
    public void ExitoListadoUsuarios(List<Usuario> listado) {

        listadoUsuarios.setAdapter(null);


        adaptadorUsuarios = new AdaptadorVendedor(getActivity(),listado);
        adaptadorUsuarios.notifyDataSetChanged();
        listadoUsuarios.setAdapter(adaptadorUsuarios);
    }

    @Override
    public void ErrorListado(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void ExitoUsuario(Usuario datos) {
        txtNombreUsuario.setText(datos.getNombreUsuario());
        txtNombre.setText(datos.getNombre());
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

    @Override
    public void ExitoEliminar(String mensaje) {
        mPresenter.cargarListado();
        mensajeError(mensaje);
    }

    @Override
    public void ErrorEliminar(String mensaje) {
        mensajeError(mensaje);
    }

    //metodo que muestra el mensaje el error si se produce alguno al enviar el usuario y contraseña
    public void mensajeError(String mensaje){
        AlertDialog malertDialog = null;
        AlertDialog.Builder mdialogBuilder = null;
        if (mdialogBuilder == null) {
            mdialogBuilder = new AlertDialog.Builder(getActivity());

            mdialogBuilder.setTitle("Vendedores");
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


    public void mostrarMensaje(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage("¿Esta seguro en eliminar al vendedor?");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mPresenter.eliminarVendedor(usuarioSelecionado.getUsuarioId());
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


}
