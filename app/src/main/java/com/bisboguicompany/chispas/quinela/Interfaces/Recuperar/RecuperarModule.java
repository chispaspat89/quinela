package com.bisboguicompany.chispas.quinela.Interfaces.Recuperar;


import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginContract;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RecuperarModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract RecuperarFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract RecuperarContract.Presenter taskPresenter(RecuperarPresenter presenter);
}
