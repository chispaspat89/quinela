package com.bisboguicompany.chispas.quinela.DI;


import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.AdminModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearJornada.CrearJornadaModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.ListVendedoresActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.ListVendedoresModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.NuevoVendedor.NuevoVendedorActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.CrearVendedores.NuevoVendedor.NuevoVendedorModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.AgregarResultados.AgregarResultadosActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.AgregarResultados.AgregarResultadosModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.ListPartidosAdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ListPartidosAdmin.ListPartidosAdminModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.NuevaJornada.NuevaJornadaActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.NuevaJornada.NuevaJornadaModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.AgregarPuntos.AgregarPuntosActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.AgregarPuntos.AgregarPuntosModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.PuntosActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.Puntos.PuntosModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.Ganadores.GanadoresActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.Ganadores.GanadoresModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.ResultadosAdminActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Administrador.ResultadosAdmin.ResultadosAdminModule;
import com.bisboguicompany.chispas.quinela.Interfaces.EditarPerfil.EditarPerfilActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.EditarPerfil.EditarPerfilModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Inicio.InicioActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Inicio.InicioModule;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.LoginModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.EquiposJornadas.EquiposJornadasActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.EquiposJornadas.EquiposJornadasModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.PrincipalModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.DetalleResultados.DetalleResultadosActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.DetalleResultados.DetalleResultadosModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.ResultadosJornadaActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Principal.ResultadosJornada.ResultadosJornadaModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Recuperar.RecuperarModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Registrarse.RegistraseActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Registrarse.RegistraseModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Ventas.EditarPërfilVendedor.PerfilVendedorActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Ventas.EditarPërfilVendedor.PerfilVendedorModule;
import com.bisboguicompany.chispas.quinela.Interfaces.Ventas.VentasActivity;
import com.bisboguicompany.chispas.quinela.Interfaces.Ventas.VentasModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity tasksActivity();


    @ActivityScoped
    @ContributesAndroidInjector(modules = RegistraseModule.class)
    abstract RegistraseActivity taskRegistrase();


    @ActivityScoped
    @ContributesAndroidInjector(modules = RecuperarModule.class)
    abstract RecuperarActivity taskRecuperar();

    @ActivityScoped
    @ContributesAndroidInjector(modules = PrincipalModule.class)
    abstract PrincipalActivity taskPrincipal();


    @ActivityScoped
    @ContributesAndroidInjector(modules = EditarPerfilModule.class)
    abstract EditarPerfilActivity taskeditarPerfil();


    @ActivityScoped
    @ContributesAndroidInjector(modules = VentasModule.class)
    abstract VentasActivity ventas();

    @ActivityScoped
    @ContributesAndroidInjector(modules = PerfilVendedorModule.class)
    abstract PerfilVendedorActivity vendedor();

    @ActivityScoped
    @ContributesAndroidInjector(modules = AdminModule.class)
    abstract AdminActivity admin();


    @ActivityScoped
    @ContributesAndroidInjector(modules = CrearJornadaModule.class)
    abstract CrearJornadaActivity crearJornada();


    @ActivityScoped
    @ContributesAndroidInjector(modules = InicioModule.class)
    abstract InicioActivity inicio();



    @ActivityScoped
    @ContributesAndroidInjector(modules = ListVendedoresModule.class)
    abstract ListVendedoresActivity listVendedores();


    @ActivityScoped
    @ContributesAndroidInjector(modules = NuevoVendedorModule.class)
    abstract NuevoVendedorActivity nuevoVendedor();


    @ActivityScoped
    @ContributesAndroidInjector(modules = EquiposJornadasModule.class)
    abstract EquiposJornadasActivity equipoJornadas();


    @ActivityScoped
    @ContributesAndroidInjector(modules = ListPartidosAdminModule.class)
    abstract ListPartidosAdminActivity partidosJOrnadasS();


    @ActivityScoped
    @ContributesAndroidInjector(modules = AgregarResultadosModule.class)
    abstract AgregarResultadosActivity agregaresultado();


    @ActivityScoped
    @ContributesAndroidInjector(modules = ResultadosAdminModule.class)
    abstract ResultadosAdminActivity resultadosAdmin();



    @ActivityScoped
    @ContributesAndroidInjector(modules = GanadoresModule.class)
    abstract GanadoresActivity ganadoresAdmin();


    @ActivityScoped
    @ContributesAndroidInjector(modules = ResultadosJornadaModule.class)
    abstract ResultadosJornadaActivity resultadoJornada();


    @ActivityScoped
    @ContributesAndroidInjector(modules = DetalleResultadosModule.class)
    abstract DetalleResultadosActivity listResultados();


    @ActivityScoped
    @ContributesAndroidInjector(modules = PuntosModule.class)
    abstract PuntosActivity puntosList();

    @ActivityScoped
    @ContributesAndroidInjector(modules = AgregarPuntosModule.class)
    abstract AgregarPuntosActivity agregarpuntos();


    @ActivityScoped
    @ContributesAndroidInjector(modules = NuevaJornadaModule.class)
    abstract NuevaJornadaActivity nuevajornadaactivity();



}
