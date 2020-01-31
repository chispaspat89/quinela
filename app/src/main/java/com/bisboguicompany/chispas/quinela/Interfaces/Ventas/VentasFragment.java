package com.bisboguicompany.chispas.quinela.Interfaces.Ventas;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bisboguicompany.chispas.quinela.Adaptadores.AdaptadorVentas;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalContract;
import com.bisboguicompany.chispas.quinela.Models.Clientes;
import com.bisboguicompany.chispas.quinela.Models.Creditos;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class VentasFragment  extends DaggerFragment implements VentasContract.View,View.OnClickListener, View.OnKeyListener, SearchView.OnQueryTextListener {


    public static VentasActivity mactivity;
    Button btneditar;

    @Inject
    VentasContract.Presenter mPresenter;



    ListView listadoUsuarios;
    AdaptadorVentas adaptadorUsuarios;
    List<Usuario> listGeneral = new ArrayList<>();
    Clientes usuarioSelecionado;
    TextView txtNombreUsuario,txtNombre;

    Usuario datosSesion;



    @Inject
    public VentasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_ventas, container, false);

        final View hView    = VentasActivity.navigationView.getHeaderView(0);

        txtNombreUsuario    = (TextView)hView.findViewById(R.id.txtNombreCompletePaciente);
        txtNombre           = (TextView)hView.findViewById(R.id.txtestado);


        listadoUsuarios = v.findViewById(R.id.listaUsuarios);
        setHasOptionsMenu(true);

        Usuario primero = new Usuario(0,
                "Jesus Israel Par Naal",
                "",
                "",
                "Chispas89",
                "jesus@hotmail.com",
                "1",
                "9991550981",
                "123456",
                "1");




        listadoUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               usuarioSelecionado = (Clientes) listadoUsuarios.getAdapter().getItem(position);
               EjecutarNotificacionErrro(usuarioSelecionado);


            }
        });
        return v;
    }

    private View alertView;
    public void EjecutarNotificacionErrro(Clientes datos){

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        alertView = inflater.inflate(R.layout.modal_creditos, null);

        EditText chivita = (EditText) alertView.findViewById(R.id.editChivita);
        EditText grande = (EditText) alertView.findViewById(R.id.editGrande);

        builder.setView(alertView).
                setCancelable(false).
                setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            int dato1 = Integer.parseInt(chivita.getText().toString());
                            int dato2 = Integer.parseInt(grande.getText().toString());

                            int idChivita = datos.getList().get(0).getIdcredito();
                            int idGrnade = datos.getList().get(1).getIdcredito();



                            if(dato1 > 0 || dato2 > 0){
                                int datoid = Integer.parseInt(String.valueOf(usuarioSelecionado.getIdcliente()));

                                int idsesion = Integer.parseInt(datosSesion.getUsuarioId());
                                mPresenter.guardarCreditos(dato1,dato2, datoid,idChivita,idGrnade,idsesion, datosSesion.getNombre());
                            }else{
                               mensajeError("Debe de ingresar la cantidad que desea agregar.");
                            }
                    }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();

    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
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
    public void ExitoListadoUsuarios(List<Clientes> listado) {

        listadoUsuarios.setAdapter(null);

        adaptadorUsuarios = new AdaptadorVentas(getActivity(),listado);
        adaptadorUsuarios.notifyDataSetChanged();
        listadoUsuarios.setAdapter(adaptadorUsuarios);
    }

    @Override
    public void ErrorListado(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void ExitoCreditos(String mensaje) {
        mensajeError(mensaje);

        mPresenter.updateUsuarios();
    }

    @Override
    public void ErrorCreditos(String mensaje) {
        mensajeError(mensaje);
    }

    @Override
    public void ExitoUsuario(Usuario datos) {
        txtNombreUsuario.setText(datos.getNombreUsuario());
        txtNombre.setText(datos.getNombre());

        datosSesion = datos;
    }


    //metodo que muestra el mensaje el error si se produce alguno al enviar el usuario y contraseña
    public void mensajeError(String mensaje){
        AlertDialog malertDialog = null;
        AlertDialog.Builder mdialogBuilder = null;
        if (mdialogBuilder == null) {
            mdialogBuilder = new AlertDialog.Builder(getActivity());

            mdialogBuilder.setTitle("Ventas");
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_listado_lecturas, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        //adapter_lecturas_new.setFilter(data);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        //adapter_lecturas_new.getFilter().filter(newText);
        if (adaptadorUsuarios !=null){
            adaptadorUsuarios.getFilter().filter(newText);
        }

        return true;
    }
}
