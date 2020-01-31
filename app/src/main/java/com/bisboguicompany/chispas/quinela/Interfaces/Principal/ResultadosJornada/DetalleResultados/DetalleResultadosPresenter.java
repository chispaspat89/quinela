package com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.DetalleResultados;

import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.ResultadosJornadaContract;

import javax.inject.Inject;

/**
 * Created by rdms on 24/10/2019.
 */

public class DetalleResultadosPresenter implements DetalleResultadosContract.Presenter {

    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private DetalleResultadosContract.View mTasksView;


    @Inject
    DetalleResultadosPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }


    @Override
    public void takeView(DetalleResultadosContract.View view) {
        this.mTasksView = view;
    }

    @Override
    public void dropView() {

    }
}
