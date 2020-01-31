package com.bisboguicompany.chispas.quinela.Interfaces.Ventas.EditarPërfilVendedor;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Ventas.VentasContract;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

public interface PerfilVendedorContract {


    interface View extends BaseView<Presenter> {

        void datosUsuario(Usuario datos);

        void exitoActualizacion();
        void ErrorActualizacion(String mensaje);

        void mensajeCargando();
        void cancelarmensaje();

        void ExitoUsuario(Usuario datos);
    }

    interface Presenter extends BasePresenter<View> {
        void cerrarSesion(Context context);
        void actualizarPerfil(Usuario objeto);
    }
}
