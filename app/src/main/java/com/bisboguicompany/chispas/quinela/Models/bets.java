package com.bisboguicompany.chispas.quinela.Models;

/**
 * Created by rdms on 24/10/2019.
 */

public class bets {

    private int id;
    private int idusuario;
    private int idpartido;
    private int credito;
    private int respuesta;
    private int idjornada;
    private int numero;
    private int idequipolocal;
    private String nombrelocal;
    private String logolocal;
    private int idequipovisitante;
    private String nombrevisitante;
    private String logovisitante;
    private String fecha;
    private int status;
    private int resultadoLocal;
    private int resultadoVisitante;
    private int tie;
    private String finalizado;

    public bets(int id, int idusuario, int idpartido, int credito, int respuesta, int idjornada, int numero, int idequipolocal, String nombrelocal, String logolocal, int idequipovisitante, String nombrevisitante, String logovisitante, String fecha, int status, int resultadoLocal, int resultadoVisitante, int tie, String finalizado) {
        this.id = id;
        this.idusuario = idusuario;
        this.idpartido = idpartido;
        this.credito = credito;
        this.respuesta = respuesta;
        this.idjornada = idjornada;
        this.numero = numero;
        this.idequipolocal = idequipolocal;
        this.nombrelocal = nombrelocal;
        this.logolocal = logolocal;
        this.idequipovisitante = idequipovisitante;
        this.nombrevisitante = nombrevisitante;
        this.logovisitante = logovisitante;
        this.fecha = fecha;
        this.status = status;
        this.resultadoLocal = resultadoLocal;
        this.resultadoVisitante = resultadoVisitante;
        this.tie = tie;
        this.finalizado = finalizado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdpartido() {
        return idpartido;
    }

    public void setIdpartido(int idpartido) {
        this.idpartido = idpartido;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public int getIdjornada() {
        return idjornada;
    }

    public void setIdjornada(int idjornada) {
        this.idjornada = idjornada;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdequipolocal() {
        return idequipolocal;
    }

    public void setIdequipolocal(int idequipolocal) {
        this.idequipolocal = idequipolocal;
    }

    public String getNombrelocal() {
        return nombrelocal;
    }

    public void setNombrelocal(String nombrelocal) {
        this.nombrelocal = nombrelocal;
    }

    public String getLogolocal() {
        return logolocal;
    }

    public void setLogolocal(String logolocal) {
        this.logolocal = logolocal;
    }

    public int getIdequipovisitante() {
        return idequipovisitante;
    }

    public void setIdequipovisitante(int idequipovisitante) {
        this.idequipovisitante = idequipovisitante;
    }

    public String getNombrevisitante() {
        return nombrevisitante;
    }

    public void setNombrevisitante(String nombrevisitante) {
        this.nombrevisitante = nombrevisitante;
    }

    public String getLogovisitante() {
        return logovisitante;
    }

    public void setLogovisitante(String logovisitante) {
        this.logovisitante = logovisitante;
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

    public int getResultadoLocal() {
        return resultadoLocal;
    }

    public void setResultadoLocal(int resultadoLocal) {
        this.resultadoLocal = resultadoLocal;
    }

    public int getResultadoVisitante() {
        return resultadoVisitante;
    }

    public void setResultadoVisitante(int resultadoVisitante) {
        this.resultadoVisitante = resultadoVisitante;
    }

    public int getTie() {
        return tie;
    }

    public void setTie(int tie) {
        this.tie = tie;
    }

    public String getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(String finalizado) {
        this.finalizado = finalizado;
    }
}
