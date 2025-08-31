package com.AndresSanchezDev.SISTEMASPURI.entity;

import jakarta.persistence.Entity;

@Entity
public class Repartidor extends Usuario {
    private String vehiculo;

    public Repartidor() {
    }

    public Repartidor(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }
}
