package com.bisboguicompany.chispas.quinela;

import android.support.annotation.VisibleForTesting;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DI.AppComponent;
import com.bisboguicompany.chispas.quinela.DI.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class AppEstructura extends DaggerApplication {

    @Inject
    RepositoryUsuario tasksRepository;


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }


    @VisibleForTesting
    public RepositoryUsuario getTasksRepository() {
        return tasksRepository;
    }



}
