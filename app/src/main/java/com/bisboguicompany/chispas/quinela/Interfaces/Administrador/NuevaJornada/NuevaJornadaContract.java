package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.NuevaJornada;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminContract;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;

import java.util.List;

public interface NuevaJornadaContract {



    interface View extends BaseView<Presenter> {

        void cerrar();
        void ExitoUsuario(Usuario datos);

        void ErrorJornada(String mensaje);
        void ExitoJornada(String mensaje);


        void mensajeCargando();
        void cancelarMensaje();
    }

    interface Presenter extends BasePresenter<View> {
        void cerrarSesion(Context context);
        void guardarJornada(String nombre, String numero, String descr);
    }


}
