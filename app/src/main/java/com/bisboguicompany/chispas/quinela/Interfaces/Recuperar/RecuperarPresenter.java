package com.bisboguicompany.chispas.quinela.Interfaces.Recuperar;

import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.Registrarse.RegistraseContract;

import javax.inject.Inject;

public class RecuperarPresenter implements RecuperarContract.Presenter {



    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private RecuperarContract.View mTasksView;


    @Inject
    RecuperarPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }




    @Override
    public void takeView(RecuperarContract.View view) {
        this.mTasksView = view;
    }

    @Override
    public void dropView() {

    }

    @Override
    public void recuperarPass(String usuario) {
        mTasksView.mensajecargando();
        mTasksRepository.recuperarContrase(usuario, new UsuarioDataSource.recuperar() {
            @Override
            public void Exito(String mensaje) {
                mTasksView.cancelarmensaje();
                mTasksView.ExitoRecuperar(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.cancelarmensaje();
                mTasksView.ErrorRecuperar(mensaje);
            }
        });
    }
}
