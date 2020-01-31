package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.NuevaJornada;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminContract;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginActivity;

import javax.inject.Inject;

public class NuevaJornadaPresenter implements NuevaJornadaContract.Presenter{



    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private NuevaJornadaContract.View mTasksView;


    @Inject
    NuevaJornadaPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }






    @Override
    public void cerrarSesion(Context context) {
        mTasksRepository.eliminarUsuario("", new UsuarioDataSource.validacioneliminado() {
            @Override
            public void validacion(String users) {
                Intent datos1 =  new Intent(context, LoginActivity.class);
                datos1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(datos1);
            }

            @Override
            public void Error(String mensaje) {

            }
        });
    }

    @Override
    public void guardarJornada(String nombre, String numero, String descr) {

        mTasksView.mensajeCargando();
        mTasksRepository.crearNuevaJornada(nombre, numero, descr, new UsuarioDataSource.guardarPuntos() {
            @Override
            public void Exito(String mensaje) {

                mTasksView.cancelarMensaje();
                mTasksView.ExitoJornada(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.cancelarMensaje();
                mTasksView.ErrorJornada(mensaje);
            }
        });
    }

    @Override
    public void takeView(NuevaJornadaContract.View view) {
        this.mTasksView =  view;
    }

    @Override
    public void dropView() {

    }
}
