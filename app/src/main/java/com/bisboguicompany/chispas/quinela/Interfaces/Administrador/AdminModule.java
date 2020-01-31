package com.bisboguicompany.chispas.quinela.Interfaces.Administrador;


import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.EditarPerfil.EditarPerfilContract;
import com.bisboguicompany.chispas.quinela.Interfaces.EditarPerfil.EditarPerfilFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.EditarPerfil.EditarPerfilPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AdminModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract AdmFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract AdminContract.Presenter taskPresenter(AdminPresenter presenter);


}
