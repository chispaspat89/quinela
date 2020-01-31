package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin;


import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ListPartidosAdminModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ListPartidosAdminFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract ListPartidosAdminContract.Presenter taskPresenter(ListPartidosAdminPresenter presenter);
}
