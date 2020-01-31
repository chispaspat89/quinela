package com.bisboguicompany.chispas.quinela.Interfaces.Recuperar;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Registrarse.RegistraseContract;

public interface RecuperarContract {

    interface View extends BaseView<Presenter> {


        void ExitoRecuperar(String mensaje);
        void ErrorRecuperar(String mensaje);

        void mensajecargando();
        void cancelarmensaje();

    }

    interface Presenter extends BasePresenter<View> {

        void recuperarPass(String usuario);
    }

}
