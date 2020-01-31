package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.NuevoVendedor;

import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarContract;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rdms on 15/10/2019.
 */

public class NuevoVendedorPresenter implements NuevoVendedorContract.Presenter {



    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private NuevoVendedorContract.View mTasksView;


    @Inject
    NuevoVendedorPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }







    @Override
    public void takeView(NuevoVendedorContract.View view) {
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
