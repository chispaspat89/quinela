package com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada;

import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rdms on 24/10/2019.
 */
@Module
public abstract class ResultadosJornadaModule {



    @FragmentScoped
    @ContributesAndroidInjector
    abstract ResultadosJornadaFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract ResultadosJornadaContract.Presenter taskPresenter(ResultadosJornadaPresenter presenter);


}
