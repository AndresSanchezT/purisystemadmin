package com.AndresSanchezDev.SISTEMASPURI.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Usuario vendedor; // solo usuarios con rol VENDEDOR

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalles;

    private Boolean aCredito;


    public Venta() {
    }

    public Venta(Long id, LocalDate fecha, Usuario vendedor, Cliente cliente, List<DetalleVenta> detalles, Boolean aCredito) {
        this.id = id;
        this.fecha = fecha;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.detalles = detalles;
        this.aCredito = aCredito;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    public Boolean getaCredito() {
        return aCredito;
    }

    public void setaCredito(Boolean aCredito) {
        this.aCredito = aCredito;
    }
}
