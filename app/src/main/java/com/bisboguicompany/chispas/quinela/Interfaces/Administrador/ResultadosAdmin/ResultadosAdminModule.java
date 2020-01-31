package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin;

import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdmFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rdms on 23/10/2019.
 */
@Module
public abstract class ResultadosAdminModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract ResultadosAdminFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract ResultadosAdminContract.Presenter taskPresenter(ResultadosAdminPresenter presenter);



}
