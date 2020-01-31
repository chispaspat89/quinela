package com.bisboguicompany.chispas.quinela.Interfaces.Inicio;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Ventas.VentasActivity;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

import java.util.List;

import javax.inject.Inject;

public class InicioPresenter implements InicioContract.Presenter{


    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private InicioContract.View mTasksView;


    @Inject
    InicioPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;


    }

    @Override
    public void takeView(InicioContract.View view) {
        this.mTasksView = view;


    }

    @Override
    public void dropView() {

    }


}
