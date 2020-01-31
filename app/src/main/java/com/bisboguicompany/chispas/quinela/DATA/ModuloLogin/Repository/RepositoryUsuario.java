package com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Repository;

import android.support.annotation.NonNull;

import com.bisboguicompany.chispas.quinela.DATA.Local;
import com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.UsuarioDataSource;
import com.bisboguicompany.chispas.quinela.DATA.Remote;
import com.bisboguicompany.chispas.quinela.Models.Clientes;
import com.bisboguicompany.chispas.quinela.Models.Creditos;
import com.bisboguicompany.chispas.quinela.Models.Equipos;
import com.bisboguicompany.chispas.quinela.Models.Ganador;
import com.bisboguicompany.chispas.quinela.Models.Partidos;
import com.bisboguicompany.chispas.quinela.Models.ResultJornada;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.Models.jornadas;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class RepositoryUsuario implements UsuarioDataSource {



    private final UsuarioDataSource mTasksRemoteDataSource;

    private final UsuarioDataSource mTasksLocalDataSource;

    Map<String, Usuario> mCachedUsuarios;

    boolean mCacheIsDirty = false;

    //inicializacion de las variables para los singleton de los metodos locales y de los metodos remotos
    @Inject
    RepositoryUsuario(@Remote UsuarioDataSource repoRemoteDataSource, @Local UsuarioDataSource repoLocalDataSource) {
        mTasksRemoteDataSource = repoRemoteDataSource;
        mTasksLocalDataSource = repoLocalDataSource;
    }



    @Override
    public void autentificacion(@NonNull String user, @NonNull String pass, @NonNull validacionCallBack call) {
        mTasksRemoteDataSource.autentificacion(user, pass, new validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {
                call.validacion(users);
            }

            @Override
            public void Error(String mensaje) {
                call.Error(mensaje);
            }
        });
    }

    @Override
    public void guardarUsuario(Usuario objeto, @NonNull validacionCallBack callFinal) {
        mTasksLocalDataSource.guardarUsuario(objeto, new validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {
                callFinal.validacion(users);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void eliminarUsuario(String idUsuario, @NonNull validacioneliminado callFinal) {
        mTasksLocalDataSource.eliminarUsuario(idUsuario, new validacioneliminado() {
            @Override
            public void validacion(String users) {
                callFinal.validacion(users);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void obtenerTodosUsuarios(@NonNull validacionCallBack callFinal) {
        mTasksLocalDataSource.obtenerTodosUsuarios(new validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {
                callFinal.validacion(users);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void guardarUsuarioServer(Usuario objeto, @NonNull validacionCallBack callFinal) {
       mTasksRemoteDataSource.guardarUsuario(objeto, new validacionCallBack() {
           @Override
           public void validacion(List<Usuario> users) {
               callFinal.validacion(users);
           }

           @Override
           public void Error(String mensaje) {
                callFinal.Error(mensaje);
           }
       });
    }

    @Override
    public void actualizarUsuarioServer(Usuario objeto, @NonNull validacionCallBack callFinal) {
        mTasksRemoteDataSource.actualizarUsuarioServer(objeto, new validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {
                mTasksLocalDataSource.actualizarUsuarioServer(objeto, new validacionCallBack() {
                    @Override
                    public void validacion(List<Usuario> users) {
                        callFinal.validacion(users);
                    }

                    @Override
                    public void Error(String mensaje) {

                    }
                });
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void guardarVendedores(Usuario objeto, @NonNull validacionCallBack callFinal) {

    }

    @Override
    public void obtenerListadoUsuarios(String idUsuario, @NonNull validacionCallBack callFinal) {
        mTasksRemoteDataSource.obtenerListadoUsuarios(idUsuario, new validacionCallBack() {
            @Override
            public void validacion(List<Usuario> users) {
                callFinal.validacion(users);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void obtenerJornadas(@NonNull listadoJornadas callFinal) {
        mTasksRemoteDataSource.obtenerJornadas(new listadoJornadas() {
            @Override
            public void Exito(List<jornadas> listjornadas) {
                callFinal.Exito(listjornadas);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void obtenerEquipos(@NonNull listadoEqupos callFinal) {
        mTasksRemoteDataSource.obtenerEquipos(new listadoEqupos() {
            @Override
            public void Exito(List<Equipos> listEquip) {
                callFinal.Exito(listEquip);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void generarCreditos(int chivita, int grande, int idUsuario, int idchivita, int idgrande, int idvendedor, String nombre, @NonNull validacioneliminado callFinal) {
        mTasksRemoteDataSource.generarCreditos(chivita, grande, idUsuario, idchivita, idgrande, idvendedor, nombre, new validacioneliminado() {
            @Override
            public void validacion(String users) {
                callFinal.validacion(users);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }



    @Override
    public void listadoCreditosUsuario(String id, @NonNull creditos callFinal) {
            mTasksRemoteDataSource.listadoCreditosUsuario(id, new creditos() {
                @Override
                public void Exito(List<Creditos> listados) {
                    callFinal.Exito(listados);
                }

                @Override
                public void Error(String mensaje) {
                    callFinal.Error(mensaje);
                }
            });
    }

    @Override
    public void obtenerPartido(int idJornadas, @NonNull listPartidos callFinal) {
        mTasksRemoteDataSource.obtenerPartido(idJornadas, new listPartidos() {
            @Override
            public void Exito(List<Partidos> listPartidos) {
                callFinal.Exito(listPartidos);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void recuperarContrase(String usuario, @NonNull recuperar callFinal) {
        mTasksRemoteDataSource.recuperarContrase(usuario, new recuperar() {
            @Override
            public void Exito(String mensaje) {
                callFinal.Exito(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void crearPartidos(JSONArray partidos, @NonNull crearPartidos callFinal) {
        mTasksRemoteDataSource.crearPartidos(partidos, new crearPartidos() {
            @Override
            public void Exito(List<Partidos> listPartidos) {
                callFinal.Exito(listPartidos);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void obtenerJornadasConpartidos(@NonNull jornadasConPartidos callFinal) {
        mTasksRemoteDataSource.obtenerJornadasConpartidos(new jornadasConPartidos() {
            @Override
            public void Exito(List<Partidos> partis, List<jornadas> listjornadas) {
                callFinal.Exito(partis,listjornadas);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void obtenerJornadasSinEquipos(@NonNull listadoJornadas callFinal) {
        mTasksRemoteDataSource.obtenerJornadasSinEquipos(new listadoJornadas() {
            @Override
            public void Exito(List<jornadas> listjornadas) {
                callFinal.Exito(listjornadas);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void guardarResultado(JSONObject resultado, @NonNull recuperar callFinal) {
        mTasksRemoteDataSource.guardarResultado(resultado, new recuperar() {
            @Override
            public void Exito(String mensaje) {
                callFinal.Exito(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void obtenerResultados(String idJornadas, @NonNull ganadoresListado callFinal) {
        mTasksRemoteDataSource.obtenerResultados(idJornadas, new ganadoresListado() {
            @Override
            public void Exito(List<Ganador> listado) {
                callFinal.Exito(listado);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void crearApuesta(JSONObject bet, @NonNull crearApuesta callFinal) {
        mTasksRemoteDataSource.crearApuesta(bet, new crearApuesta() {
            @Override
            public void Exito(String mensaje) {
                callFinal.Exito(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void obtenerListadoClientes(String tipoUser, @NonNull listadoClientes callFinal) {
        mTasksRemoteDataSource.obtenerListadoClientes(tipoUser, new listadoClientes() {
            @Override
            public void Exito(List<Clientes> listado) {
                callFinal.Exito(listado);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void obtenerResultadosUsuario(String idUsuario, String idJornada, @NonNull listResultados callFinal) {
        mTasksRemoteDataSource.obtenerResultadosUsuario(idUsuario, idJornada, new listResultados() {
            @Override
            public void Exito(List<ResultJornada> listado) {
                callFinal.Exito(listado);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void eliminarVendedores(String idUsuario, @NonNull eliminarVendedores callFinal) {
        mTasksRemoteDataSource.eliminarVendedores(idUsuario, new eliminarVendedores() {
            @Override
            public void Exito(String mensaje) {
                callFinal.Exito(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void eliminarTablas(@NonNull eliminarTablas callFinal) {
        mTasksRemoteDataSource.eliminarTablas(new eliminarTablas() {
            @Override
            public void Exito(String mensaje) {
                callFinal.Exito(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void generarReporteJornada(String idjornada, String tipo, @NonNull generarReporte callFinal) {
        mTasksRemoteDataSource.generarReporteJornada(idjornada, tipo, new generarReporte() {
            @Override
            public void Exito(String mensaje) {
                callFinal.Exito(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void guardarPuntos(String cantidad, String jornada, String cantiChivita, @NonNull guardarPuntos callFinal) {
        mTasksRemoteDataSource.guardarPuntos(cantidad, jornada, cantiChivita, new guardarPuntos() {
            @Override
            public void Exito(String mensaje) {
                callFinal.Exito(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }




    @Override
    public void cerrarJornada(String idjornada, @NonNull guardarPuntos callFinal) {
        mTasksRemoteDataSource.cerrarJornada(idjornada, new guardarPuntos() {
            @Override
            public void Exito(String mensaje) {
                callFinal.Exito(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }

    @Override
    public void crearNuevaJornada(String nombre, String numero, String descripcion, @NonNull guardarPuntos callFinal) {
        mTasksRemoteDataSource.crearNuevaJornada(nombre, numero, descripcion, new guardarPuntos() {
            @Override
            public void Exito(String mensaje) {
                callFinal.Exito(mensaje);
            }

            @Override
            public void Error(String mensaje) {
                callFinal.Error(mensaje);
            }
        });
    }


}
