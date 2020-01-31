package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaContract;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginActivity;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rdms on 15/10/2019.
 */

public class ListVendedoresPresenter implements ListVendedoresContract.Presenter{


    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private ListVendedoresContract.View mTasksView;


    @Inject
    ListVendedoresPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }







    @Override
    public void takeView(ListVendedoresContract.View view) {
        this.mTasksView = view;

        cargarListadoUsuarios();

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


    public  void cargarListadoUsuarios(){
        mTasksRepository.obtenerListadoUsuarios("2", new UsuarioDataSource.validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {
                mTasksView.ExitoListadoUsuarios(users);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.ErrorListado(mensaje);
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
    public void eliminarVendedor(String idUsuario) {

        mTasksView.mensajecargando();
        mTasksRepository.eliminarVendedores(idUsuario, new UsuarioDataSource.eliminarVendedores() {
            @Override
            public void Exito(String mensaje) {

                mTasksView.cancelarcargando();
                mTasksView.ExitoEliminar(mensaje);

            }

            @Override
            public void Error(String mensaje) {

                mTasksView.cancelarcargando();
                mTasksView.ErrorListado(mensaje);

            }
        });
    }

    @Override
    public void cargarListado() {
        mTasksRepository.obtenerListadoUsuarios("2", new UsuarioDataSource.validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {
                mTasksView.ExitoListadoUsuarios(users);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.ErrorListado(mensaje);
            }
        });
    }
}
