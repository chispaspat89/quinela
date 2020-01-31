package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.NuevaJornada;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.ListVendedoresActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.PuntosActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.ResultadosAdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.ResultadosAdminFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.ResultadosAdminPresenter;
import com.bisboguicompany.chispas.quinela.R;
import com.bisboguicompany.chispas.quinela.Utils.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.HasFragmentInjector;
import dagger.android.support.DaggerAppCompatActivity;

public class NuevaJornadaActivity extends DaggerAppCompatActivity implements HasFragmentInjector {





    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";
    private DrawerLayout mDrawerLayout;




    @Inject
    NuevaJornadaPresenter mTasksPresenter;


    @Inject
    Lazy<NuevaJornadaFragment> taskFragmentProvider;


    public static NavigationView navigationView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_jornada);



        ActionBar ab = getSupportActionBar();
        ab.setTitle("Nueva jornada");
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
        NuevaJornadaFragment tasksFragment =
                (NuevaJornadaFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
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

                                Intent princi =  new Intent(NuevaJornadaActivity.this, AdminActivity.class);
                                startActivity(princi);

                                break;
                            case R.id.itemeditar:
                                break;

                            case R.id.itemvendedor:
                                Intent vende =  new Intent(NuevaJornadaActivity.this, ListVendedoresActivity.class);
                                startActivity(vende);
                                break;

                            case R.id.itemPuntos:
                                Intent puntos =  new Intent(NuevaJornadaActivity.this, PuntosActivity.class);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(NuevaJornadaActivity.this);

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
