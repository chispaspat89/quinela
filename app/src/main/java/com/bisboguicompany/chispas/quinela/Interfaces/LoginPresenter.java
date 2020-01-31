package com.bisboguicompany.chispas.quinela.Interfaces;


import android.content.Context;
import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

import java.util.List;

import javax.inject.Inject;

@ActivityScoped
public class LoginPresenter implements LoginContract.Presenter  {

    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private LoginContract.View mTasksView;


    @Inject
    LoginPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
        pasar();

    }

    public void pasar(){

        mTasksRepository.obtenerTodosUsuarios(new UsuarioDataSource.validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {
                if (users.get(0).getTipoUsuario().equals("1")){
                    mTasksView.irAdmin();
                }else if(users.get(0).getTipoUsuario().equals("2")){
                    mTasksView.irVendedores();
                }else if(users.get(0).getTipoUsuario().equals("3")){
                    mTasksView.irUsuarios();
                }
            }

            @Override
            public void Error(String mensaje) {

            }
        });
    }


    @Override
    public void guardarRed(String nombre, String tipo, String token, String id) {

    }

    @Override
    public void initSesion(String user, String pass, Context context) {

        mTasksView.showloading();
        mTasksRepository.autentificacion(user, pass, new UsuarioDataSource.validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {
                mTasksView.cancelarLoading();

                mTasksRepository.guardarUsuario(users.get(0), new UsuarioDataSource.validacionCallBack() {
                    @Override
                    public void validacion(List<Usuario> users) {

                        if (users.get(0).getTipoUsuario().equals("1")){
                            //admin
                            mTasksView.irAdmin();
                        }else if(users.get(0).getTipoUsuario().equals("2")){
                            //vendedor
                            mTasksView.irVendedores();
                        }else if (users.get(0).getTipoUsuario().equals("3")){
                            //usuarios
                            mTasksView.irUsuarios();
                        }
                    }

                    @Override
                    public void Error(String mensaje) {
                        mTasksView.cancelarLoading();
                        mTasksView.showEmptyUserError(mensaje);
                    }
                });
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.cancelarLoading();
                mTasksView.showEmptyUserError(mensaje);
            }
        });
    }

    @Override
    public void guardarRedSocial(Usuario user) {

    }

    @Override
    public void validarRed(String idRed, String tipo) {

    }

    @Override
    public void enviarLlavesNotificaciones(String idUsuario, String tokenUsuario, String playerIdNotificaciones) {

    }

    @Override
    public void guardarSesionUsuario(String idUsuario, String pass) {

    }

    @Override
    public void obtenerListadoProductores(String token, String idUsuario, Usuario datos, List<Usuario> listado) {

    }

    @Override
    public void takeView(LoginContract.View view) {
        this.mTasksView = view;
    }

    @Override
    public void dropView() {

    }
}
