package com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalContract;
import com.bisboguicompany.chispas.quinela.Models.Creditos;
import com.bisboguicompany.chispas.quinela.Models.ResultJornada;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;

import java.util.List;

/**
 * Created by rdms on 24/10/2019.
 */

public interface ResultadosJornadaContract {


    interface View extends BaseView<Presenter> {

        void ExitoResultados(List<ResultJornada> listado);
        void ErrorResultados(String mensaje);

        void mensajecargando();
        void cancelarmensaje();

    }

    interface Presenter extends BasePresenter<View> {

        void obtenerresultados(String idjornada);
    }



}
