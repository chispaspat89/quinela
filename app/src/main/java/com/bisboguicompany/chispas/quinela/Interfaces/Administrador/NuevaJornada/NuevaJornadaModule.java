package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.NuevaJornada;


import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdmFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class NuevaJornadaModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract NuevaJornadaFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract NuevaJornadaContract.Presenter taskPresenter(NuevaJornadaPresenter presenter);




}
