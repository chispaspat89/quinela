package com.bisboguicompany.chispas.quinela.Models;

/**
 * Created by rdms on 18/10/2019.
 */

public class Apuesta {

    private int id;
    private int idjornada;
    private int idEquipoLocal;
    private int idEquipoVisitante;
    private String fecha;
    private int status;
    private int partidos;
    private int ganador;
    private String urlLocal;
    private String urlVisitante;
    private String nombrelocal;
    private String nombrevisitante;


    public Apuesta(int id, int idjornada, int idEquipoLocal, int idEquipoVisitante, String fecha, int status, int partidos, int ganador, String urlLocal, String urlVisitante, String nombrelocal, String nombrevisitante) {
        this.id = id;
        this.idjornada = idjornada;
        this.idEquipoLocal = idEquipoLocal;
        this.idEquipoVisitante = idEquipoVisitante;
        this.fecha = fecha;
        this.status = status;
        this.partidos = partidos;
        this.ganador = ganador;
        this.urlLocal = urlLocal;
        this.urlVisitante = urlVisitante;
        this.nombrelocal = nombrelocal;
        this.nombrevisitante = nombrevisitante;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdjornada() {
        return idjornada;
    }

    public void setIdjornada(int idjornada) {
        this.idjornada = idjornada;
    }

    public int getIdEquipoLocal() {
        return idEquipoLocal;
    }

    public void setIdEquipoLocal(int idEquipoLocal) {
        this.idEquipoLocal = idEquipoLocal;
    }

    public int getIdEquipoVisitante() {
        return idEquipoVisitante;
    }

    public void setIdEquipoVisitante(int idEquipoVisitante) {
        this.idEquipoVisitante = idEquipoVisitante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPartidos() {
        return partidos;
    }

    public void setPartidos(int partidos) {
        this.partidos = partidos;
    }

    public int getGanador() {
        return ganador;
    }

    public void setGanador(int ganador) {
        this.ganador = ganador;
    }

    public String getUrlLocal() {
        return urlLocal;
    }

    public void setUrlLocal(String urlLocal) {
        this.urlLocal = urlLocal;
    }

    public String getUrlVisitante() {
        return urlVisitante;
    }

    public void setUrlVisitante(String urlVisitante) {
        this.urlVisitante = urlVisitante;
    }

    public String getNombrelocal() {
        return nombrelocal;
    }

    public void setNombrelocal(String nombrelocal) {
        this.nombrelocal = nombrelocal;
    }

    public String getNombrevisitante() {
        return nombrevisitante;
    }

    public void setNombrevisitante(String nombrevisitante) {
        this.nombrevisitante = nombrevisitante;
    }
}
