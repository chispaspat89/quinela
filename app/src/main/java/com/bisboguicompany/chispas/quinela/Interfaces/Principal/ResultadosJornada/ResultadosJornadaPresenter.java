package com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada;

import android.support.annotation.Nullable;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalContract;
import com.bisboguicompany.chispas.quinela.Models.ResultJornada;
import com.bisboguicompany.chispas.quinela.Models.Usuario;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rdms on 24/10/2019.
 */

public class ResultadosJornadaPresenter implements ResultadosJornadaContract.Presenter{


    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private ResultadosJornadaContract.View mTasksView;


    @Inject
    ResultadosJornadaPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }


    @Override
    public void takeView(ResultadosJornadaContract.View view) {
        this.mTasksView = view;
    }

    @Override
    public void dropView() {

    }

    @Override
    public void obtenerresultados(String idjornada) {
        mTasksRepository.obtenerTodosUsuarios(new UsuarioDataSource.validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {

                mTasksRepository.obtenerResultadosUsuario(users.get(0).getUsuarioId(), idjornada, new UsuarioDataSource.listResultados() {
                    @Override
                    public void Exito(List<ResultJornada> listado) {
                        mTasksView.ExitoResultados(listado);
                    }

                    @Override
                    public void Error(String mensaje) {

                        mTasksView.ErrorResultados(mensaje);
                    }
                });

            }

            @Override
            public void Error(String mensaje) {

            }
        });
    }
}
