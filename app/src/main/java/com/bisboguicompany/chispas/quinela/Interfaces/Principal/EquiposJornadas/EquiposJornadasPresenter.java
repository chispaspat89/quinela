package com.bisboguicompany.chispas.quinela.Interfaces.Principal.EquiposJornadas;

import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalContract;
import com.bisboguicompany.chispas.quinela.Models.Creditos;
import com.bisboguicompany.chispas.quinela.Models.Equipos;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rdms on 17/10/2019.
 */

public class EquiposJornadasPresenter implements EquiposJornadasContract.Presenter {



    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private EquiposJornadasContract.View mTasksView;


    @Inject
    EquiposJornadasPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }





    @Override
    public void takeView(EquiposJornadasContract.View view) {
        this.mTasksView = view;

        obtenerCreditosUsuario();
    }

    @Override
    public void dropView() {

    }


    public void obtenerCreditosUsuario(){

        mTasksRepository.obtenerTodosUsuarios(new UsuarioDataSource.validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {

                mTasksView.ExitoUsuario(users.get(0));

                mTasksRepository.listadoCreditosUsuario(users.get(0).getUsuarioId(), new UsuarioDataSource.creditos() {
                    @Override
                    public void Exito(List<Creditos> listados) {
                        mTasksView.ExitoCreditos(listados);
                    }

                    @Override
                    public void Error(String mensaje) {

                    }
                });
            }

            @Override
            public void Error(String mensaje) {

            }
        });

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
    public void guardarApuesta(JSONObject jsonGuardar) {

        mTasksView.cargarmensaje();
        mTasksRepository.crearApuesta(jsonGuardar, new UsuarioDataSource.crearApuesta() {
            @Override
            public void Exito(String mensaje) {

                mTasksView.cancelarmensaje();
                mTasksView.ExitoCrearApuesta(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.cancelarmensaje();
                mTasksView.ErrorCrearApuesta(mensaje);
            }
        });
    }
}
