package com.bisboguicompany.chispas.quinela.Interfaces.Principal;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarContract;
import com.bisboguicompany.chispas.quinela.Models.Creditos;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;

import java.util.List;

public interface PrincipalContract {

    interface View extends BaseView<Presenter> {


        void cerrar();
        void ExitoCreditos(List<Creditos> list);

        void ExitoJornadas(List<jornadas> listJornadas);
        void ErrorJorndas(String mensaje);

        void ExitoUsuario(Usuario datos);
    }

    interface Presenter extends BasePresenter<View> {
        void cerrarSesion(Context context);
        void obtenerCreditos(String idUsuario);
        void obtenerJOrnadasActivas();
    }


}
