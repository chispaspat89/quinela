package com.bisboguicompany.chispas.quinela.DI;

import android.app.Application;

import com.bisboguicompany.chispas.quinela.AppEstructura;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.RepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {RepositoryModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})


public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(AppEstructura application);

    RepositoryUsuario getTasksRepository();



    @Override
    void inject(DaggerApplication instance);

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }



}
