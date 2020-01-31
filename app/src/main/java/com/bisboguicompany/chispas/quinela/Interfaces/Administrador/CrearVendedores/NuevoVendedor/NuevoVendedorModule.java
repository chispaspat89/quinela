package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.NuevoVendedor;

import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.ListVendedoresContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.ListVendedoresFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.ListVendedoresPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rdms on 15/10/2019.
 */
@Module
public abstract class NuevoVendedorModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract NuevoVendedorFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract NuevoVendedorContract.Presenter taskPresenter(NuevoVendedorPresenter presenter);



}
