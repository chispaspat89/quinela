package com.bisboguicompany.chispas.quinela.Interfaces.Registrarse;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginContract;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

import java.util.List;

public interface RegistraseContract {


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
