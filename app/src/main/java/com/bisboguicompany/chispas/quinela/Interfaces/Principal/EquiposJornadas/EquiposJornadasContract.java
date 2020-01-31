package com.bisboguicompany.chispas.quinela.Interfaces.Principal.EquiposJornadas;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalContract;
import com.bisboguicompany.chispas.quinela.Models.Creditos;
import com.bisboguicompany.chispas.quinela.Models.Equipos;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by rdms on 17/10/2019.
 */

public interface EquiposJornadasContract {

    interface View extends BaseView<Presenter> {


       void ExitoPartidos(List<Partidos> listpartidos);
       void ErrorPartidos(String mensaje);

        void ExitoEquipos(List<Partidos>listpartidos, List<Equipos> listEquipos);
        void ErrorEquipos(String mensaje);

        void ExitoCreditos(List<Creditos> list);
        void ErrorCreditos(String mensaje);


        void ExitoUsuario(Usuario user);

        void ExitoCrearApuesta(String mensaje);
        void ErrorCrearApuesta(String mensaje);

        void cargarmensaje();
        void cancelarmensaje();
    }

    interface Presenter extends BasePresenter<View> {

        void obtenerPartidos(int idJornada);
        void obtenrEquipos(List<Partidos> listpartidos);
        void guardarApuesta(JSONObject jsonGuardar);



    }



}
