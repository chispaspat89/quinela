package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.AgregarPuntos;

import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaContract;

import javax.inject.Inject;

/**
 * Created by rdms on 01/11/2019.
 */

public class AgregarPuntosPresenter implements AgregarPuntosContract.Presenter {


    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private AgregarPuntosContract.View mTasksView;


    @Inject
    AgregarPuntosPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }







    @Override
    public void takeView(AgregarPuntosContract.View view) {
        this.mTasksView = view;
    }

    @Override
    public void dropView() {

    }



    @Override
    public void guardarCantidad(String cantidad, String jornada, String cantiChivita) {


        mTasksView.mensajecargando();
        mTasksRepository.guardarPuntos(cantidad, jornada, cantiChivita, new UsuarioDataSource.guardarPuntos() {
            @Override
            public void Exito(String mensaje) {
                mTasksView.cancelarmensaje();
                mTasksView.ExtioGuardado(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.cancelarmensaje();
                mTasksView.ErrorGuardado(mensaje);
            }
        });



    }
}
