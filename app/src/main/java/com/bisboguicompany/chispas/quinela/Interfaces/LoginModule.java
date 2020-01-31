package com.bisboguicompany.chispas.quinela.Interfaces;


import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LoginModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract LoginFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract LoginContract.Presenter taskPresenter(LoginPresenter presenter);
}
