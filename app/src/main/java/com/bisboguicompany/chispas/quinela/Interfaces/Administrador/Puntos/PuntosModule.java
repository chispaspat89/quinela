package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos;

import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdmFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rdms on 01/11/2019.
 */
@Module
public abstract class PuntosModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract PuntosFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract PuntosContract.Presenter taskPresenter(PuntosPresenter presenter);

}
