package com.bisboguicompany.chispas.quinela.Interfaces.EditarPerfil;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarContract;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

import java.util.List;

import javax.inject.Inject;

public class EditarPerfilPresenter  implements EditarPerfilContract.Presenter{

    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private EditarPerfilContract.View mTasksView;


    @Inject
    EditarPerfilPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }


    @Override
    public void takeView(EditarPerfilContract.View view) {
        this.mTasksView = view;

        mTasksRepository.obtenerTodosUsuarios(new UsuarioDataSource.validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {
                mTasksView.datosUsuario(users.get(0));
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
    public void actualizarPerfil(Usuario objeto) {
        mTasksView.mensajeCargando();
        mTasksRepository.actualizarUsuarioServer(objeto, new UsuarioDataSource.validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {
                mTasksView.cancelarmensaje();
                mTasksView.exitoActualizacion();
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.cancelarmensaje();
                mTasksView.ErrorActualizacion(mensaje);
            }
        });
    }
}
