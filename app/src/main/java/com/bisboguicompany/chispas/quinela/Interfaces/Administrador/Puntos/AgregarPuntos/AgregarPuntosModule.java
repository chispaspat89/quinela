package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.AgregarPuntos;

import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.PuntosContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.PuntosFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.PuntosPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rdms on 01/11/2019.
 */
@Module
public abstract class AgregarPuntosModule {



    @FragmentScoped
    @ContributesAndroidInjector
    abstract AgregarPuntosFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract AgregarPuntosContract.Presenter taskPresenter(AgregarPuntosPresenter presenter);
}
