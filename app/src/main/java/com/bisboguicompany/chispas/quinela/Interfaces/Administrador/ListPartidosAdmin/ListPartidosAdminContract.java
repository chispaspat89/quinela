package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Models.Equipos;
import com.bisboguicompany.chispas.quinela.Models.Partidos;

import java.util.List;

public interface ListPartidosAdminContract {


    interface View extends BaseView<Presenter> {

        void ExitoPartidos(List<Partidos> listpartidos);
        void ErrorPartidos(String mensaje);


        void ExitoEquipos(List<Partidos>listpartidos, List<Equipos> listEquipos);
        void ErrorEquipos(String mensaje);


        void mensajecargando();
        void cancelarmensaje();
    }

    interface Presenter extends BasePresenter<View> {
        void obtenerPartidos(int idJornada);
        void obtenrEquipos(List<Partidos> listpartidos);
        void cerrar(String jornada);
    }

}
