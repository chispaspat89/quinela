package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.NuevoVendedor;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarContract;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

/**
 * Created by rdms on 15/10/2019.
 */

public interface NuevoVendedorContract {

    interface View extends BaseView<Presenter> {


        void exitoGuardado();
        void ErrorGuardado(String mensaje);

        void mensajeCargando();
        void cancelarMensaje();


    }

    interface Presenter extends BasePresenter<View> {
        void guardarInformacion(Usuario objeto);
    }


}
