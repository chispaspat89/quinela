package com.bisboguicompany.chispas.quinela.Models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

@Entity(tableName = "usuario")
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    public final int idUser;

    @Nullable
    @ColumnInfo(name = "nombre")
    public final String nombre;

    @Nullable
    @ColumnInfo(name = "apellidoPaterno")
    public final String apellidoPaterno;

    @Nullable
    @ColumnInfo(name = "apellidoMaterno")
    public final String apellidoMaterno;

    @Nullable
    @ColumnInfo(name = "nombreUsuario")
    public final String nombreUsuario;

    @Nullable
    @ColumnInfo(name = "correo")
    public final String correo;


    @Nullable
    @ColumnInfo(name = "tipoUsuario")
    public final String tipoUsuario;

    @Nullable
    @ColumnInfo(name = "telefono")
    public final String telefono;

    @Nullable
    @ColumnInfo(name = "contrasenia")
    public final String contrasenia;

    @Nullable
    @ColumnInfo(name = "usuarioId")
    public final String usuarioId;

    public Usuario(int idUser, @Nullable String nombre, @Nullable String apellidoPaterno, @Nullable String apellidoMaterno, @Nullable String nombreUsuario, @Nullable String correo, @Nullable String tipoUsuario, @Nullable String telefono, @Nullable String contrasenia, @Nullable String usuarioId) {
        this.idUser = idUser;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.tipoUsuario = tipoUsuario;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.usuarioId = usuarioId;
    }

    @Nullable
    public String getUsuarioId() {
        return usuarioId;
    }

    public int getIdUser() {
        return idUser;
    }

    @Nullable
    public String getNombre() {
        return nombre;
    }

    @Nullable
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    @Nullable
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    @Nullable
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    @Nullable
    public String getCorreo() {
        return correo;
    }

    @Nullable
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    @Nullable
    public String getTelefono() {
        return telefono;
    }

    @Nullable
    public String getContrasenia() {
        return contrasenia;
    }
}
