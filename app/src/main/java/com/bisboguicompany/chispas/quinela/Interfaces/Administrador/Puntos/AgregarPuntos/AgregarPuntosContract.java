package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.AgregarPuntos;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.PuntosContract;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;

import java.util.List;

/**
 * Created by rdms on 01/11/2019.
 */

public interface AgregarPuntosContract {


    interface View extends BaseView<Presenter> {

        void ExtioGuardado(String mensaje);
        void ErrorGuardado(String mensaje);

        void mensajecargando();
        void cancelarmensaje();

    }

    interface Presenter extends BasePresenter<View> {
        void guardarCantidad(String cantidad, String jornada, String cantiChivita);
    }
}
