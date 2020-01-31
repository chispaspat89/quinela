package com.bisboguicompany.chispas.quinela.Interfaces.Administrador;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.EditarPerfil.EditarPerfilContract;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;

import java.util.List;

public interface AdminContract {



    interface View extends BaseView<Presenter> {

        void cerrar();
        void ExitoUsuario(Usuario datos);

        void ExitoJornadasPartidos(List<Partidos> partidos, List<jornadas> listjornadas);
        void ErrorJornadasPartidos(String mensaje);
    }

    interface Presenter extends BasePresenter<View> {
            void cerrarSesion(Context context);
            void obtenerJornadasactivas();
    }

}
