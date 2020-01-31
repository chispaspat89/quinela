package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada;

import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminContract;
import com.bisboguicompany.chispas.quinela.Models.Equipos;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.Models.jornadas;

import org.json.JSONArray;

import java.util.List;

import javax.inject.Inject;

public class CrearJornadaPresenter implements CrearJornadaContract.Presenter {

    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private CrearJornadaContract.View mTasksView;


    @Inject
    CrearJornadaPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }




    @Override
    public void takeView(CrearJornadaContract.View view) {
        this.mTasksView = view;

        obtenerJOrnadasActivas();

        obtenerEquipos();
    }

    @Override
    public void dropView() {

    }

    @Override
    public void obtenerJOrnadasActivas() {
            mTasksRepository.obtenerJornadasSinEquipos(new UsuarioDataSource.listadoJornadas() {
                @Override
                public void Exito(List<jornadas> listjornadas) {
                    mTasksView.ExitoJornadas(listjornadas);
                }

                @Override
                public void Error(String mensaje) {
                    mTasksView.ErrorJorndas(mensaje);
                }
            });
    }

    @Override
    public void obtenerEquipos() {
        mTasksRepository.obtenerEquipos(new UsuarioDataSource.listadoEqupos() {
            @Override
            public void Exito(List<Equipos> listEquip) {
                mTasksView.ExitoEquipos(listEquip);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.ErroEquipos(mensaje);
            }
        });
    }

    @Override
    public void guardarPartidos(JSONArray objSend) {
        mTasksView.mensajecargando();
        mTasksRepository.crearPartidos(objSend, new UsuarioDataSource.crearPartidos() {
            @Override
            public void Exito(List<Partidos> listPartidos) {

                mTasksView.cancelarmensajecargando();
                mTasksView.ExitoCreacion();
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.cancelarmensajecargando();
                mTasksView.ErrorCreacion(mensaje);
            }
        });
    }
}
