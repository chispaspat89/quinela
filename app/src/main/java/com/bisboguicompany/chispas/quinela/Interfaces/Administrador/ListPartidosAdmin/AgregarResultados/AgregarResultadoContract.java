package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.AgregarResultados;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.ListPartidosAdminContract;
import com.bisboguicompany.chispas.quinela.Models.Equipos;
import com.bisboguicompany.chispas.quinela.Models.Partidos;

import org.json.JSONObject;

import java.util.List;

public interface AgregarResultadoContract {


    interface View extends BaseView<Presenter> {

        void ExitoGuardado(String mensaje);
        void ErrorGuardado(String mensaje);

        void mensajecargando();
        void cancelarcartando();

    }

    interface Presenter extends BasePresenter<View> {

        void guardarResultado(JSONObject respuesta);
    }
}
