package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;

import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdmFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminPresenter;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaPresenter;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.NuevaJornada.NuevaJornadaActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.PuntosActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.ResultadosAdminActivity;
import com.bisboguicompany.chispas.quinela.R;
import com.bisboguicompany.chispas.quinela.Utils.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.HasFragmentInjector;
import dagger.android.support.DaggerAppCompatActivity;

public class ListVendedoresActivity extends DaggerAppCompatActivity implements HasFragmentInjector {


    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";
    private DrawerLayout mDrawerLayout;




    @Inject
    ListVendedoresPresenter mTasksPresenter;


    @Inject
    Lazy<ListVendedoresFragment> taskFragmentProvider;


    public static NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vendedores);



        ActionBar ab = getSupportActionBar();
        ab.setTitle("Listado de vendedores");
        ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);


        // Set up the navigation drawer.
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);

        navigationView = findViewById(R.id.nav_view);



        //NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        ListVendedoresFragment tasksFragment =
                (ListVendedoresFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (tasksFragment == null) {
            // Get the fragment from dagger
            tasksFragment = taskFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
        }


    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){

                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }else{
                    mDrawerLayout.openDrawer(GravityCompat.START);

                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupDrawerContent(NavigationView navigationView) {


        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {


                        switch (menuItem.getItemId()) {
                            case R.id.itementrada:
                                Intent prin =  new Intent(ListVendedoresActivity.this, AdminActivity.class);
                                startActivity(prin);

                                break;
                            case R.id.itemeditar:

                                Intent resu =  new Intent(ListVendedoresActivity.this, ResultadosAdminActivity.class);
                                startActivity(resu);

                                break;

                            case R.id.itemvendedor:
                                Intent vende =  new Intent(ListVendedoresActivity.this, ListVendedoresActivity.class);
                                startActivity(vende);
                                break;

                            case R.id.itemPuntos:
                                Intent puntos =  new Intent(ListVendedoresActivity.this, PuntosActivity.class);
                                startActivity(puntos);
                                break;


                            case R.id.itemCerrarSesion:
                                //este es cerrar sesion
                                CerrarSesion();
                                break;
                            default:
                                break;
                        }
                        // Close the navigation drawer when an item is selected.
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }



    public void CerrarSesion(){

        AlertDialog.Builder builder = new AlertDialog.Builder(ListVendedoresActivity.this);

        builder.setTitle("GrandeMx")
                .setMessage("¿Esta seguro que quiere cerrar su sesión?")
                .setCancelable(false)
                .setPositiveButton("SI",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog2, int which) {
                                mTasksPresenter.cerrarSesion(getApplicationContext());
                            }
                        })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog2, int which) {

                    }
                });


        builder.create();
        builder.show();

    }

    //con este boton bloqueamos el return a la vista principal si el usuario presiona dos veces la misma opcion en el menu
    @Override
    public void onBackPressed() {
        Log.d("Back","para regresar");
    }





    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No sólo wifi, también GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle debería no ser tan ñapa
        for (int i = 0; i < 2; i++) {
            // ¿Tenemos conexión? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }

}
