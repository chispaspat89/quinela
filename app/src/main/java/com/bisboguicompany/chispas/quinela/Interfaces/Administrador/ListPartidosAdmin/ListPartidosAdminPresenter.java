package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin;

import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminContract;
import com.bisboguicompany.chispas.quinela.Models.Equipos;
import com.bisboguicompany.chispas.quinela.Models.Partidos;

import java.util.List;

import javax.inject.Inject;

public class ListPartidosAdminPresenter implements ListPartidosAdminContract.Presenter{

    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private ListPartidosAdminContract.View mTasksView;


    @Inject
    ListPartidosAdminPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }



    @Override
    public void takeView(ListPartidosAdminContract.View view) {
        this.mTasksView = view;
    }

    @Override
    public void dropView() {

    }

    @Override
    public void obtenerPartidos(int idJornada) {
        mTasksRepository.obtenerPartido(idJornada, new UsuarioDataSource.listPartidos() {
            @Override
            public void Exito(List<Partidos> listPartidos) {
                mTasksView.ExitoPartidos(listPartidos);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.ErrorPartidos(mensaje);
            }
        });
    }

    @Override
    public void obtenrEquipos(List<Partidos> listpartidos) {
        mTasksRepository.obtenerEquipos(new UsuarioDataSource.listadoEqupos() {
            @Override
            public void Exito(List<Equipos> listEquip) {
                mTasksView.ExitoEquipos(listpartidos,listEquip);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.ErrorEquipos(mensaje);
            }
        });
    }

    @Override
    public void cerrar(String jornada) {

        mTasksView.mensajecargando();
        mTasksRepository.cerrarJornada(jornada, new UsuarioDataSource.guardarPuntos() {
            @Override
            public void Exito(String mensaje) {
                mTasksView.cancelarmensaje();
                mTasksView.ErrorEquipos(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.cancelarmensaje();
                mTasksView.ErrorEquipos(mensaje);
            }
        });

    }
}
