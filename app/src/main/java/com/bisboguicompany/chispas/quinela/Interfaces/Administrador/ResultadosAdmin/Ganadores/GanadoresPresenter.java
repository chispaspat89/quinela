package com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.Ganadores;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository.RepositoryUsuario;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.AgregarResultados.AgregarResultadoContract;
import com.bisboguicompany.chispas.quinela.Models.Ganador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by rdms on 23/10/2019.
 */

public class GanadoresPresenter implements GanadoresContract.Presenter {



    private final RepositoryUsuario mTasksRepository;

    @Nullable
    private GanadoresContract.View mTasksView;


    @Inject
    GanadoresPresenter(RepositoryUsuario tasksRepository) {
        mTasksRepository = tasksRepository;
    }





    @Override
    public void takeView(GanadoresContract.View view) {
        this.mTasksView = view;
    }

    @Override
    public void dropView() {

    }

    @Override
    public void obtenerResultados(int idJornada) {
        mTasksRepository.obtenerResultados(String.valueOf(idJornada), new UsuarioDataSource.ganadoresListado() {
            @Override
            public void Exito(List<Ganador> listado) {

                mTasksView.ExitoResultados(listado);

            }

            @Override
            public void Error(String mensaje) {

                mTasksView.ErrorResultados(mensaje);

            }
        });
    }

    @Override
    public void generarDocumento(String idjornada, String tipo) {


        mTasksView.mensajecargando();
        mTasksRepository.generarReporteJornada(idjornada, tipo, new UsuarioDataSource.generarReporte() {
            @Override
            public void Exito(String mensaje) {
                mTasksView.cancelarmensaje();
                mTasksView.ExitoGenerado(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                mTasksView.cancelarmensaje();
                mTasksView.ErrorGenerado(mensaje);
            }
        });
    }





}
