package com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Local;

import android.support.annotation.NonNull;

import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Utils.AppExecutors;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class LocalUsuarioDataSource implements UsuarioDataSource {


    private final UsuarioDao mTasksDao;
    private final AppExecutors mAppExecutors;

    @Inject
    public LocalUsuarioDataSource(@NonNull AppExecutors executors, @NonNull UsuarioDao tasksDao) {
        mTasksDao = tasksDao;
        mAppExecutors = executors;
    }


    @Override
    public void autentificacion(@NonNull String user, @NonNull String pass, @NonNull validacionCallBack call) {

    }

    @Override
    public void guardarUsuario(Usuario objeto, @NonNull validacionCallBack callFinal) {
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {

                mTasksDao.insertUsuario(objeto);
                final List<Usuario> envs = mTasksDao.getObtenerUsuarios();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (envs.size()>0) {

                            callFinal.validacion(envs);
                        }else {

                            callFinal.Error("Se produjo un error al guardar el usuario.");
                        }
                    }
                });

            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void eliminarUsuario(String idUsuario, @NonNull validacioneliminado callFinal) {
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                mTasksDao.eliminarUsuarios();
                final List<Usuario> envs = mTasksDao.getObtenerUsuarios();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (envs.size()>0) {

                            callFinal.Error("Se produjo un error al eliminar la sesion.");
                        }else {

                           callFinal.validacion("Se elimino con exito los usuarios.");
                        }
                    }
                });

            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void obtenerTodosUsuarios(@NonNull validacionCallBack callFinal) {
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                final List<Usuario> envs = mTasksDao.getObtenerUsuarios();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (envs.size()>0) {

                            callFinal.validacion(envs);
                        }else {

                            callFinal.Error("Se produjo un error al obtener el usuario.");
                        }
                    }
                });

            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void guardarUsuarioServer(Usuario objeto, @NonNull validacionCallBack callFinal) {

    }

    @Override
    public void actualizarUsuarioServer(Usuario objeto, @NonNull validacionCallBack callFinal) {
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {

                mTasksDao.actualizarUsuario(objeto.getCorreo(),objeto.getTelefono(),objeto.getContrasenia(),objeto.getUsuarioId());

                final List<Usuario> envs = mTasksDao.getObtenerUsuarioPerfil(objeto.getUsuarioId());
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (envs.size()>0) {

                            callFinal.validacion(envs);
                        }else {

                            callFinal.Error("Se produjo un error al obtener el usuario.");
                        }
                    }
                });

            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void guardarVendedores(Usuario objeto, @NonNull validacionCallBack callFinal) {

    }

    @Override
    public void obtenerListadoUsuarios(String idUsuario, @NonNull validacionCallBack callFinal) {

    }

    @Override
    public void obtenerJornadas(@NonNull listadoJornadas callFinal) {

    }

    @Override
    public void obtenerEquipos(@NonNull listadoEqupos callFinal) {

    }

    @Override
    public void generarCreditos(int chivita, int grande, int idUsuario, int idchivita, int idgrande, int idvendedor, String nombre, @NonNull validacioneliminado callFinal) {


    }




    @Override
    public void listadoCreditosUsuario(String id, @NonNull creditos callFinal) {

    }

    @Override
    public void obtenerPartido(int idJornadas, @NonNull listPartidos callFinal) {

    }

    @Override
    public void recuperarContrase(String usuario, @NonNull recuperar callFinal) {

    }

    @Override
    public void crearPartidos(JSONArray partidos, @NonNull crearPartidos callFinal) {

    }

    @Override
    public void obtenerJornadasConpartidos(@NonNull jornadasConPartidos callFinal) {

    }

    @Override
    public void obtenerJornadasSinEquipos(@NonNull listadoJornadas callFinal) {

    }

    @Override
    public void guardarResultado(JSONObject resultado, @NonNull recuperar callFinal) {

    }

    @Override
    public void obtenerResultados(String idJornadas, @NonNull ganadoresListado callFinal) {

    }

    @Override
    public void crearApuesta(JSONObject bet, @NonNull crearApuesta callFinal) {

    }

    @Override
    public void obtenerListadoClientes(String tipoUser, @NonNull listadoClientes callFinal) {

    }

    @Override
    public void obtenerResultadosUsuario(String idUsuario, String idJornada, @NonNull listResultados callFinal) {

    }

    @Override
    public void eliminarVendedores(String idUsuario, @NonNull eliminarVendedores callFinal) {

    }

    @Override
    public void eliminarTablas(@NonNull eliminarTablas callFinal) {

    }

    @Override
    public void generarReporteJornada(String idjornada, String tipo, @NonNull generarReporte callFinal) {

    }

    @Override
    public void guardarPuntos(String cantidad, String jornada, String cantiChivita, @NonNull guardarPuntos callFinal) {

    }




    @Override
    public void cerrarJornada(String idjornada, @NonNull guardarPuntos callFinal) {

    }

    @Override
    public void crearNuevaJornada(String nombre, String numero, String descripcion, @NonNull guardarPuntos callFinal) {

    }


}
