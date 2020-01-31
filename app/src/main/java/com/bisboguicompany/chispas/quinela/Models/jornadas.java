package com.bisboguicompany.chispas.quinela.Models;

/**
 * Created by rdms on 17/10/2019.
 */

public class jornadas {

    private int id;
    private String nombre;
    private int numero;
    private int idjornada;
    private int puntos;
    private int puntoschivita;
    private int status;


    public jornadas(int id, String nombre, int numero, int idjornada, int puntos, int puntoschivita, int status) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.idjornada = idjornada;
        this.puntos = puntos;
        this.puntoschivita = puntoschivita;
        this.status = status;
    }

    public int getPuntoschivita() {
        return puntoschivita;
    }

    public void setPuntoschivita(int puntoschivita) {
        this.puntoschivita = puntoschivita;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
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

    public int getIdjornada() {
        return idjornada;
    }

    public void setIdjornada(int idjornada) {
        this.idjornada = idjornada;
    }
}
