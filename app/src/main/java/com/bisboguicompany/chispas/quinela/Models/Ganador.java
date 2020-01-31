package com.bisboguicompany.chispas.quinela.Models;

/**
 * Created by rdms on 23/10/2019.
 */

public class Ganador {

    private int id;
    private int idJornada;
    private int totalgenera;
    private int totalgrande;
    private int totalchivita;


    public Ganador(int id, int idJornada, int totalgenera, int totalgrande, int totalchivita) {
        this.id = id;
        this.idJornada = idJornada;
        this.totalgenera = totalgenera;
        this.totalgrande = totalgrande;
        this.totalchivita = totalchivita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(int idJornada) {
        this.idJornada = idJornada;
    }

    public int getTotalgenera() {
        return totalgenera;
    }

    public void setTotalgenera(int totalgenera) {
        this.totalgenera = totalgenera;
    }

    public int getTotalgrande() {
        return totalgrande;
    }

    public void setTotalgrande(int totalgrande) {
        this.totalgrande = totalgrande;
    }

    public int getTotalchivita() {
        return totalchivita;
    }

    public void setTotalchivita(int totalchivita) {
        this.totalchivita = totalchivita;
    }
}
