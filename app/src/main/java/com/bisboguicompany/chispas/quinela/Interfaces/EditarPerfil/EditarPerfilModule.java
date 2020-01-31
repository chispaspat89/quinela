package com.bisboguicompany.chispas.quinela.Interfaces.EditarPerfil;


import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class EditarPerfilModule {



    @FragmentScoped
    @ContributesAndroidInjector
    abstract EditarPerfilFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract EditarPerfilContract.Presenter taskPresenter(EditarPerfilPresenter presenter);
}
