package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminContract;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;

import java.util.List;

/**
 * Created by rdms on 01/11/2019.
 */

public interface PuntosContract {


    interface View extends BaseView<Presenter> {

        void cerrar();
        void ExitoUsuario(Usuario datos);

        void ExitoJornadasPartidos(List<jornadas> listjornadas);
        void ErrorJornadasPartidos(String mensaje);
    }

    interface Presenter extends BasePresenter<View> {
        void cerrarSesion(Context context);
        void obtenerJornadasactivas();
    }

}
