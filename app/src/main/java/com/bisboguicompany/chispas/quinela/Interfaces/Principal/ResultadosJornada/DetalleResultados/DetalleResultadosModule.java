package com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.DetalleResultados;

import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.ResultadosJornadaContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.ResultadosJornadaFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.ResultadosJornadaPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rdms on 24/10/2019.
 */
@Module
public abstract class DetalleResultadosModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract DetalleResultadosFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract DetalleResultadosContract.Presenter taskPresenter(DetalleResultadosPresenter presenter);


}
