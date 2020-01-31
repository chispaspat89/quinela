package com.bisboguicompany.chispas.quinela.Interfaces.Registrarse;


import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginContract;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RegistraseModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract RegistraseFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract RegistraseContract.Presenter taskPresenter(RegistrasePresenter presenter);


}
