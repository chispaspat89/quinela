package com.bisboguicompany.chispas.quinela.Interfaces.Principal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarContract;
import com.bisboguicompany.chispas.quinela.Models.Creditos;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;

import java.util.List;

import javax.inject.Inject;

public class PrincipalPresenter  implements PrincipalContract.Presenter {


    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private PrincipalContract.View mTasksView;


    @Inject
    PrincipalPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }




    @Override
    public void takeView(PrincipalContract.View view) {
        this.mTasksView = view;

        obtenerJOrnadasActivas();

        mTasksRepository.obtenerTodosUsuarios(new UsuarioDataSource.validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {

                mTasksView.ExitoUsuario(users.get(0));
                obtenerCreditos(users.get(0).getUsuarioId());
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
    public void obtenerCreditos(String idUsuario) {

        mTasksRepository.listadoCreditosUsuario(idUsuario, new UsuarioDataSource.creditos() {
            @Override
            public void Exito(List<Creditos> listados) {
                mTasksView.ExitoCreditos(listados);
            }

            @Override
            public void Error(String mensaje) {

            }
        });
    }

    @Override
    public void obtenerJOrnadasActivas() {
        /*mTasksRepository.obtenerJornadas(new UsuarioDataSource.listadoJornadas() {
            @Override
            public void Exito(List<jornadas> listjornadas) {
                mTasksView.ExitoJornadas(listjornadas);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.ErrorJorndas(mensaje);
            }
        });*/


        mTasksRepository.obtenerJornadasConpartidos(new UsuarioDataSource.jornadasConPartidos() {
            @Override
            public void Exito(List<Partidos> partis, List<jornadas> listjornadas) {
               // mTasksView.ExitoJornadasPartidos(partis,listjornadas);
                mTasksView.ExitoJornadas(listjornadas);
            }

            @Override
            public void Error(String mensaje) {
                //mTasksView.ErrorJornadasPartidos(mensaje);
                mTasksView.ErrorJorndas(mensaje);
            }
        });
    }
}
