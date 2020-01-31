package com.bisboguicompany.chispas.quinela.Models;

public class seleccionados {
    private int id;
    private int idspin;
    private int idequipo;


    public seleccionados(int id, int idspin, int idequipo) {
        this.id = id;
        this.idspin = idspin;
        this.idequipo = idequipo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdspin() {
        return idspin;
    }

    public void setIdspin(int idspin) {
        this.idspin = idspin;
    }

    public int getIdequipo() {
        return idequipo;
    }

    public void setIdequipo(int idequipo) {
        this.idequipo = idequipo;
    }
}
