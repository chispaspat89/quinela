package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores;

import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rdms on 15/10/2019.
 */
@Module
public abstract class ListVendedoresModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract ListVendedoresFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract ListVendedoresContract.Presenter taskPresenter(ListVendedoresPresenter presenter);


}
