package com.bisboguicompany.chispas.quinela.Interfaces.Inicio;

import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.bisboguicompany.chispas.quinela.Interfaces.LoginFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginPresenter;
import com.bisboguicompany.chispas.quinela.R;
import com.bisboguicompany.chispas.quinela.Utils.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.HasFragmentInjector;
import dagger.android.support.DaggerAppCompatActivity;

public class InicioActivity extends DaggerAppCompatActivity implements HasFragmentInjector {


    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";


    @Inject
    InicioPresenter mTasksPresenter;

    @Inject
    Lazy<InicioFragment> taskFragmentProvider;

    private DrawerLayout mDrawerLayout;

    @Inject
    InicioFragment fragment;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        //********************************* Metodo para obtener el id del usuario **************************************
        InicioFragment statisticsFragment = (InicioFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (statisticsFragment == null) {
            statisticsFragment = fragment;
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    statisticsFragment, R.id.contentFrame);
        }


    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START);
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




}
