package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaContract;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

import java.util.List;

/**
 * Created by rdms on 15/10/2019.
 */

public interface ListVendedoresContract {


    interface View extends BaseView<Presenter> {

        void ExitoListadoUsuarios(List<Usuario> listado);
        void ErrorListado(String mensaje);
        void ExitoUsuario(Usuario datos);

        void mensajecargando();
        void cancelarcargando();

        void ExitoEliminar(String mensaje);
        void ErrorEliminar(String mensaje);
    }

    interface Presenter extends BasePresenter<View> {
        void cerrarSesion(Context context);
        void eliminarVendedor(String idUsuario);
        void cargarListado();
    }


}
