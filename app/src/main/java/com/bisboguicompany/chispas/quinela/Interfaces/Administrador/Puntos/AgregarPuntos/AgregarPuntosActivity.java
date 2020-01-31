package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.AgregarPuntos;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaPresenter;
import com.bisboguicompany.chispas.quinela.R;
import com.bisboguicompany.chispas.quinela.Utils.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class AgregarPuntosActivity extends DaggerAppCompatActivity {




    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";
    private DrawerLayout mDrawerLayout;


    @Inject
    AgregarPuntosPresenter mTasksPresenter;


    @Inject
    Lazy<AgregarPuntosFragment> taskFragmentProvider;


    public NavigationView navigationView;

    public void onFragmentInteraction(String title) {
        getSupportActionBar().setTitle(title);
    }


    @Inject
    AgregarPuntosFragment fragment;

    AgregarPuntosFragment taskDetailFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_puntos);


        taskDetailFragment = (AgregarPuntosFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);


        if (taskDetailFragment == null) {
            taskDetailFragment = fragment;
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    taskDetailFragment, R.id.contentFrame);
        }
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //mDrawerLayout.openDrawer(GravityCompat.START);
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.d("---->","----->: " + requestCode);
        fragment.onActivityResult(requestCode,resultCode,data);
    }


}
