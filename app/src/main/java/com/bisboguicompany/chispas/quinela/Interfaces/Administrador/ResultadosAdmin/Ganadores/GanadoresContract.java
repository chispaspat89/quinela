package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.Ganadores;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.ResultadosAdminContract;
import com.bisboguicompany.chispas.quinela.Models.Ganador;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;

import java.util.List;

/**
 * Created by rdms on 23/10/2019.
 */

public interface GanadoresContract {



    interface View extends BaseView<Presenter> {


        void ExitoResultados(List<Ganador> listadosGanadores);
        void ErrorResultados(String mensaje);

        void mensajecargando();
        void cancelarmensaje();

        void ExitoGenerado(String mensaje);
        void ErrorGenerado(String mensaje);

    }

    interface Presenter extends BasePresenter<View> {

        void obtenerResultados(int idJornada);
        void generarDocumento(String idjornada, String tipo);
    }


}
