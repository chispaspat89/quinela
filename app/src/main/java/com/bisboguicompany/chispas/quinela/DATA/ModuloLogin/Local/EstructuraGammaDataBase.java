package com.bisboguicompany.chispas.quinela.DATA.ModuloLogin.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.bisboguicompany.chispas.quinela.Models.Usuario;


@Database(entities = {Usuario.class}, version = 3, exportSchema = true)
public abstract class EstructuraGammaDataBase  extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();
}
