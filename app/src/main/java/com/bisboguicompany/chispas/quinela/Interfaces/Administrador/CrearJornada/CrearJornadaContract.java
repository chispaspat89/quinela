package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminContract;
import com.bisboguicompany.chispas.quinela.Models.Equipos;
import com.bisboguicompany.chispas.quinela.Models.jornadas;

import org.json.JSONArray;

import java.util.List;

public interface CrearJornadaContract {


    interface View extends BaseView<Presenter> {

        void ExitoJornadas(List<jornadas> listJornadas);
        void ErrorJorndas(String mensaje);


        void ExitoEquipos(List<Equipos> listEquipos);
        void ErroEquipos(String mensaje);

        void mensajecargando();
        void cancelarmensajecargando();

        void ExitoCreacion();
        void ErrorCreacion(String mensaje);
    }

    interface Presenter extends BasePresenter<View> {
        void obtenerJOrnadasActivas();

        void obtenerEquipos();
        void guardarPartidos(JSONArray objSend);
    }

}
