package com.bisboguicompany.chispas.quinela.Models;

/**
 * Created by rdms on 17/10/2019.
 */

public class Equipos {


    private int id;
    private String nombre;
    private int idteam;
    private String logo;

    public Equipos(int id, String nombre, int idteam, String logo) {
        this.id = id;
        this.nombre = nombre;
        this.idteam = idteam;
        this.logo = logo;
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

    public int getIdteam() {
        return idteam;
    }

    public void setIdteam(int idteam) {
        this.idteam = idteam;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
