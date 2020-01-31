package com.bisboguicompany.chispas.quinela.DATA.ModuloLogin;

import android.support.annotation.NonNull;

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

public interface UsuarioDataSource {

    interface CargarUsuarioCallback {
        void onTasksLoaded(List<Usuario> tasks);
        void onDataNotAvailable();
    }

    interface validacionCallBack{
        void validacion(List<Usuario> users);
        void Error(String mensaje);
    }

    interface validacioneliminado{
        void validacion(String users);
        void Error(String mensaje);
    }


    interface listadoJornadas{
        void Exito(List<jornadas> listjornadas);
        void Error(String mensaje);
    }

      interface listadoEqupos{
        void Exito(List<Equipos> listEquip);
        void Error(String mensaje);
    }

    interface creditos{
        void Exito(List<Creditos> listados);
        void Error(String mensaje);
    }

    interface listPartidos{
        void Exito(List<Partidos> listPartidos);
        void Error(String mensaje);
    }
    interface recuperar{
        void Exito(String mensaje);
        void Error(String mensaje);
    }


    interface crearPartidos{
        void Exito(List<Partidos> listPartidos);
        void Error(String mensaje);
    }

    interface jornadasConPartidos{
        void Exito(List<Partidos> Jornadas,List<jornadas> listjornadas);
        void Error(String mensaje);
    }


    interface ganadoresListado{
        void Exito(List<Ganador> listado);
        void Error(String mensaje);
    }

    interface crearApuesta{
        void Exito(String mensaje);
        void Error(String mensaje);
    }

    interface listadoClientes{
        void Exito(List<Clientes> listado);
        void Error(String mensaje);
    }

    interface listResultados{
        void Exito(List<ResultJornada> listado);
        void Error(String mensaje);
    }


    interface eliminarVendedores{
        void Exito(String mensaje);
        void Error(String mensaje);
    }

    interface eliminarTablas{
        void Exito(String mensaje);
        void Error(String mensaje);
    }

    interface generarReporte{
        void Exito(String mensaje);
        void Error(String mensaje);
    }

    interface guardarPuntos{
        void Exito(String mensaje);
        void Error(String mensaje);
    }


    void autentificacion(@NonNull String user, @NonNull String pass, @NonNull validacionCallBack call);
    void guardarUsuario(Usuario objeto, @NonNull validacionCallBack callFinal);
    void eliminarUsuario(String idUsuario, @NonNull validacioneliminado callFinal);
    void obtenerTodosUsuarios(@NonNull validacionCallBack callFinal);

    void guardarUsuarioServer(Usuario objeto, @NonNull validacionCallBack callFinal);
    void actualizarUsuarioServer(Usuario objeto, @NonNull validacionCallBack callFinal);

    void guardarVendedores(Usuario objeto, @NonNull validacionCallBack callFinal);
    void obtenerListadoUsuarios(String idUsuario,@NonNull validacionCallBack callFinal);
    void obtenerJornadas(@NonNull listadoJornadas callFinal);
    void obtenerEquipos(@NonNull listadoEqupos callFinal);
    void generarCreditos(int chivita, int grande, int idUsuario,int idchivita,int idgrande ,int idvendedor, String nombre,@NonNull validacioneliminado callFinal);
    void listadoCreditosUsuario(String id,@NonNull creditos callFinal );

    void obtenerPartido(int idJornadas,@NonNull listPartidos callFinal );
    void recuperarContrase(String usuario,@NonNull recuperar callFinal );

    void crearPartidos(JSONArray partidos,@NonNull crearPartidos callFinal);

    void obtenerJornadasConpartidos(@NonNull jornadasConPartidos callFinal);
    void obtenerJornadasSinEquipos(@NonNull listadoJornadas callFinal);
    void guardarResultado(JSONObject resultado, @NonNull recuperar callFinal);

    void obtenerResultados(String idJornadas,@NonNull ganadoresListado callFinal );
    void crearApuesta(JSONObject bet, @NonNull crearApuesta callFinal);

    void obtenerListadoClientes(String tipoUser,@NonNull listadoClientes callFinal);
    void obtenerResultadosUsuario(String idUsuario, String idJornada, @NonNull listResultados callFinal);


    void eliminarVendedores(String idUsuario, @NonNull eliminarVendedores callFinal);
    void eliminarTablas(@NonNull eliminarTablas callFinal);

    void generarReporteJornada(String idjornada,String tipo,@NonNull generarReporte callFinal);

    void guardarPuntos(String cantidad,String jornada,String cantiChivita,@NonNull guardarPuntos callFinal );
    void cerrarJornada(String idjornada, @NonNull guardarPuntos callFinal);


    void crearNuevaJornada(String nombre, String numero, String descripcion, @NonNull guardarPuntos callFinal);
}
