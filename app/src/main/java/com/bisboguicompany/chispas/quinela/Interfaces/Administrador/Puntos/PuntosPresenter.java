package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminContract;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginActivity;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rdms on 01/11/2019.
 */

public class PuntosPresenter implements PuntosContract.Presenter{

    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private PuntosContract.View mTasksView;


    @Inject
    PuntosPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }






    @Override
    public void takeView(PuntosContract.View view) {
        this.mTasksView = view;

        obtenerJornadasactivas();
        mTasksRepository.obtenerTodosUsuarios(new UsuarioDataSource.validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {
                mTasksView.ExitoUsuario(users.get(0));
            }

            @Override
            public void Error(String mensaje) {

            }
        });
    }

    @Override
    public void dropView() {

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
    public void obtenerJornadasactivas() {
        mTasksRepository.obtenerJornadasConpartidos(new UsuarioDataSource.jornadasConPartidos() {
            @Override
            public void Exito(List<Partidos> partis, List<jornadas> listjornadas) {
                mTasksView.ExitoJornadasPartidos(listjornadas);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.ErrorJornadasPartidos(mensaje);
            }
        });
    }
}
