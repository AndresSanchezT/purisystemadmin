package com.AndresSanchezDev.SISTEMASPURI.entity;

import jakarta.persistence.Entity;

@Entity
public class Vendedor extends Usuario {
    private String zona;

    public Vendedor() {
    }

    public Vendedor(String zona) {
        this.zona = zona;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}