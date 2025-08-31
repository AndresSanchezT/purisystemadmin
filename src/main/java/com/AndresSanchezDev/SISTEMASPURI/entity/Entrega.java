package com.AndresSanchezDev.SISTEMASPURI.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaEntrega;

    private String direccion;

    @ManyToOne
    @JoinColumn(name = "repartidor_id")
    private Usuario repartidor; // solo usuarios con rol REPARTIDOR

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    public Entrega() {
    }

    public Entrega(Long id, LocalDate fechaEntrega, String direccion, Usuario repartidor, Venta venta) {
        this.id = id;
        this.fechaEntrega = fechaEntrega;
        this.direccion = direccion;
        this.repartidor = repartidor;
        this.venta = venta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuario getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Usuario repartidor) {
        this.repartidor = repartidor;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
