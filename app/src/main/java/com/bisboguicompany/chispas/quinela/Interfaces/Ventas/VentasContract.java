package com.bisboguicompany.chispas.quinela.Interfaces.Ventas;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Registrarse.RegistraseContract;
import com.bisboguicompany.chispas.quinela.Models.Clientes;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

import java.util.List;

public interface VentasContract {

    interface View extends BaseView<Presenter> {
        void ExitoListadoUsuarios(List<Clientes> listado);
        void ErrorListado(String mensaje);

        void ExitoCreditos(String mensaje);
        void ErrorCreditos(String mensaje);

        void ExitoUsuario(Usuario datos);
    }

    interface Presenter extends BasePresenter<View> {
        void cerrarSesion(Context context);
        void guardarCreditos(int chivita,int grande,int idUsuario,int idchivita,int idgrande, int iduser, String nombre);
        void updateUsuarios();
    }

}
