package com.bisboguicompany.chispas.quinela.Interfaces.Registrarse;

import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginContract;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

import java.util.List;

import javax.inject.Inject;

public class RegistrasePresenter  implements RegistraseContract.Presenter{


    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private RegistraseContract.View mTasksView;


    @Inject
    RegistrasePresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }



    @Override
    public void takeView(RegistraseContract.View view) {
        this.mTasksView = view;
    }

    @Override
    public void dropView() {

    }

    @Override
    public void guardarInformacion(Usuario objeto) {

        mTasksView.mensajeCargando();

        mTasksRepository.guardarUsuarioServer(objeto, new UsuarioDataSource.validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {
                mTasksView.cancelarMensaje();
                mTasksView.exitoGuardado();
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.cancelarMensaje();
                mTasksView.ErrorGuardado(mensaje);
            }
        });

    }
}
