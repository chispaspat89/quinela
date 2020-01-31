package com.bisboguicompany.chispas.quinela.Models;

import java.util.List;

/**
 * Created by rdms on 24/10/2019.
 */

public class ResultJornada {

    private int id;
    private String nombre;
    private int numero;
    private String listBest;
    private int besttotal;
    private String mensaje;


    public ResultJornada(int id, String nombre, int numero, String listBest, int besttotal, String mensaje) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.listBest = listBest;
        this.besttotal = besttotal;
        this.mensaje = mensaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getListBest() {
        return listBest;
    }

    public void setListBest(String listBest) {
        this.listBest = listBest;
    }

    public int getBesttotal() {
        return besttotal;
    }

    public void setBesttotal(int besttotal) {
        this.besttotal = besttotal;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
