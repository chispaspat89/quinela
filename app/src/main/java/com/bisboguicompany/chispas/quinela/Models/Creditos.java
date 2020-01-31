package com.bisboguicompany.chispas.quinela.Models;

/**
 * Created by rdms on 23/10/2019.
 */

public class Creditos {

    private int id;
    private int idUsuario;
    private int cantidadgrande;
    private int cantidadchica;
    private String nombre;
    private int tipo;
    private int idcredito;


    public Creditos(int id, int idUsuario, int cantidadgrande, int cantidadchica, String nombre, int tipo, int idcredito) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.cantidadgrande = cantidadgrande;
        this.cantidadchica = cantidadchica;
        this.nombre = nombre;
        this.tipo = tipo;
        this.idcredito = idcredito;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCantidadgrande() {
        return cantidadgrande;
    }

    public void setCantidadgrande(int cantidadgrande) {
        this.cantidadgrande = cantidadgrande;
    }

    public int getCantidadchica() {
        return cantidadchica;
    }

    public void setCantidadchica(int cantidadchica) {
        this.cantidadchica = cantidadchica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getIdcredito() {
        return idcredito;
    }

    public void setIdcredito(int idcredito) {
        this.idcredito = idcredito;
    }
}
