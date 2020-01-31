package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.Ganadores;

import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.ResultadosAdminContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.ResultadosAdminFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.ResultadosAdminPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rdms on 23/10/2019.
 */
@Module
public abstract class GanadoresModule {



    @FragmentScoped
    @ContributesAndroidInjector
    abstract GanadoresFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract GanadoresContract.Presenter taskPresenter(GanadoresPresenter presenter);


}
