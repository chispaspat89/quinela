package com.bisboguicompany.chispas.quinela.Interfaces.Inicio;

import android.content.Context;

import com.bisboguicompany.chispas.quinela.BasePresenter;
import com.bisboguicompany.chispas.quinela.BaseView;
import com.bisboguicompany.chispas.quinela.Interfaces.Ventas.VentasContract;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

public interface InicioContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
