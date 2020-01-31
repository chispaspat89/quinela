package com.bisboguicompany.chispas.quinela.DATA;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Local.EstructuraGammaDataBase;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Local.LocalUsuarioDataSource;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Local.UsuarioDao;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Remote.RemoteUsuarioDataSource;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Utils.AppExecutors;
import com.bisboguicompany.chispas.quinela.Utils.DiskIOThreadExecutor;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Provides;

@dagger.Module
abstract public class RepositoryModule {


    private static final int THREAD_COUNT = 3;

    @Singleton
    @Binds
    @Local
    abstract UsuarioDataSource provideLocalDataSource(LocalUsuarioDataSource dataSource);

    @Singleton
    @Binds
    @Remote
    abstract UsuarioDataSource provideRemoteDataSource(RemoteUsuarioDataSource dataSource);



    @Singleton
    @Provides
    static UsuarioDao provideTasksDao(EstructuraGammaDataBase db) {
        return db.usuarioDao();
    }




    @Singleton
    @Provides
    static EstructuraGammaDataBase provideDb(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), EstructuraGammaDataBase.class, "apicultores.db")
                . fallbackToDestructiveMigration ()
                .build();
    }



    @Singleton
    @Provides
    static AppExecutors provideAppExecutors() {
        return new AppExecutors(new DiskIOThreadExecutor(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                new AppExecutors.MainThreadExecutor());
    }
}
