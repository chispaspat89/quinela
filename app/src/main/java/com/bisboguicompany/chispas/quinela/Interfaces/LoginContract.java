package com.bisboguicompany.chispas.quinela.Interfaces;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

import java.util.List;

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);



        void showEmptyUserError(String mensaje);
        void showloading();
        void cancelarLoading();
        void errorAutentificacion(String msj);

        void showUserList();
        boolean isActive();
        void showPrincipal();
        void sesion(Usuario datos, String pass);
        void irUsuarios();
        void irVendedores();
        void irAdmin();



    }

    interface Presenter extends BasePresenter<View> {
        void guardarRed(String nombre, String tipo, String token, String id);
        void initSesion(String user, String pass, Context context);
        void guardarRedSocial(Usuario user);
        void validarRed(String idRed, String tipo);
        void enviarLlavesNotificaciones(String idUsuario, String tokenUsuario, String playerIdNotificaciones);

        void guardarSesionUsuario(String idUsuario, String pass);
        void obtenerListadoProductores(String token, String idUsuario, Usuario datos, List<Usuario> listado);

    }



}
