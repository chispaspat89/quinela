package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.AgregarResultados;

import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.ListPartidosAdminContract;

import org.json.JSONObject;

import javax.inject.Inject;

public class AgregarResultadoPresenter implements AgregarResultadoContract.Presenter {



    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private AgregarResultadoContract.View mTasksView;


    @Inject
    AgregarResultadoPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }



    @Override
    public void takeView(AgregarResultadoContract.View view) {
        this.mTasksView = view;
    }

    @Override
    public void dropView() {

    }

    @Override
    public void guardarResultado(JSONObject respuesta) {
        mTasksView.mensajecargando();
        mTasksRepository.guardarResultado(respuesta, new UsuarioDataSource.recuperar() {
            @Override
            public void Exito(String mensaje) {
                mTasksView.cancelarcartando();
                mTasksView.ExitoGuardado(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.cancelarcartando();
                mTasksView.ErrorGuardado(mensaje);
            }
        });
    }
}
