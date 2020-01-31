package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminContract;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;

import java.util.List;

/**
 * Created by rdms on 23/10/2019.
 */

public interface ResultadosAdminContract {




    interface View extends BaseView<Presenter> {

        void cerrar();
        void ExitoUsuario(Usuario datos);

        void ExitoJornadasPartidos(List<Partidos> partidos, List<jornadas> listjornadas);
        void ErrorJornadasPartidos(String mensaje);


        void ExitoReinicio(String mensaje);
        void ErrorReinicio(String mensaje);

        void mensajecargando();
        void cancelarcargando();

    }

    interface Presenter extends BasePresenter<View> {
        void cerrarSesion(Context context);
        void obtenerJornadasactivas();

        void reiniciar();
    }

}
