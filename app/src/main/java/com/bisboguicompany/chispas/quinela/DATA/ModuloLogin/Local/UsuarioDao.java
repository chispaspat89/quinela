package com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Local;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bisboguicompany.chispas.quinela.Models.Usuario;

import java.util.List;

@android.arch.persistence.room.Dao
public interface UsuarioDao {

    //metodo para obtener todos los usuarios que se encuentran en el dispositivo
    @Query("SELECT * FROM usuario")
    List<Usuario> getObtenerUsuarios();


    //metodo para registrar un nuevo usuario en el dispositivo
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsuario(Usuario us);

    //metodo para eliminar a todos los usuarios del dispositivo
    @Query("DELETE FROM usuario")
    void eliminarUsuarios();


    //metodo para obtener a un usuario en particular
    @Query("SELECT * FROM usuario WHERE usuarioId = :idUsuario")
    List<Usuario> getObtenerUsuarioPerfil(String idUsuario);


    //metodo que se encarga de actualizar la informacion del usuario
    @Query("UPDATE usuario SET correo = :correo, telefono = :telefono, contrasenia = :contrasenia WHERE usuarioId = :idUsuario")
    void actualizarUsuario(String correo, String telefono, String contrasenia, String idUsuario);


    //metodo para guardar todos los usuarios del sistema web
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarTodosUsuarios(List<Usuario> list);

    //metodo para obtener todos los usuarios que se encuentran en el dispositivo
    @Query("SELECT * FROM usuario WHERE contrasenia = :pass AND nombreUsuario = :nomUsuario")
    List<Usuario> autentificacionLocal(String pass, String nomUsuario);


}
