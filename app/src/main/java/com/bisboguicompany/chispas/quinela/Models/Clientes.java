package com.bisboguicompany.chispas.quinela.Models;

import java.util.List;

/**
 * Created by rdms on 24/10/2019.
 */

public class Clientes {

    private int id;
    private int rol;
    private String name;
    private String username;
    private String telefono;
    private String mail;
    private int idcliente;
    private List<Creditos> list;


    public Clientes(int id, int rol, String name, String username, String telefono, String mail, int idcliente, List<Creditos> list) {
        this.id = id;
        this.rol = rol;
        this.name = name;
        this.username = username;
        this.telefono = telefono;
        this.mail = mail;
        this.idcliente = idcliente;
        this.list = list;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public List<Creditos> getList() {
        return list;
    }

    public void setList(List<Creditos> list) {
        this.list = list;
    }
}
