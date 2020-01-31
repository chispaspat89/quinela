package com.bisboguicompany.chispas.quinela.Interfaces.Principal.EquiposJornadas;

import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rdms on 17/10/2019.
 */
@Module
public abstract class EquiposJornadasModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract EquiposJornadasFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract EquiposJornadasContract.Presenter taskPresenter(EquiposJornadasPresenter presenter);

}
