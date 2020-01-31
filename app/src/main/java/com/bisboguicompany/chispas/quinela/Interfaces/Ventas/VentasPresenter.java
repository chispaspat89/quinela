package com.bisboguicompany.chispas.quinela.Interfaces.Ventas;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Registrarse.RegistraseContract;
import com.bisboguicompany.chispas.quinela.Models.Clientes;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

import java.util.List;

import javax.inject.Inject;

public class VentasPresenter  implements VentasContract.Presenter {



    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private VentasContract.View mTasksView;


    @Inject
    VentasPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }


    @Override
    public void takeView(VentasContract.View view) {
        this.mTasksView = view;

        obtenerListUsuarios();

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

    public void obtenerListUsuarios(){


        mTasksRepository.obtenerListadoClientes("3", new UsuarioDataSource.listadoClientes() {
            @Override
            public void Exito(List<Clientes> listado) {
                mTasksView.ExitoListadoUsuarios(listado);
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
    public void guardarCreditos(int chivita, int grande, int idUsuario, int idchivita, int idgrande, int iduser, String nombre) {
        mTasksRepository.generarCreditos(chivita, grande, idUsuario, idchivita, idgrande, iduser, nombre, new UsuarioDataSource.validacioneliminado() {
            @Override
            public void validacion(String users) {
                mTasksView.ExitoCreditos(users);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.ErrorCreditos(mensaje);
            }
        });
    }


    @Override
    public void updateUsuarios() {
        mTasksRepository.obtenerListadoClientes("3", new UsuarioDataSource.listadoClientes() {
            @Override
            public void Exito(List<Clientes> listado) {
                mTasksView.ExitoListadoUsuarios(listado);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.ErrorListado(mensaje);
            }
        });
    }
}
