package com.bisboguicompany.chispas.quinela.Interfaces.Principal;


import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PrincipalModule {


    @FragmentScoped
    @ContributesAndroidInjector
    abstract PrincipalFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract PrincipalContract.Presenter taskPresenter(PrincipalPresenter presenter);


}
