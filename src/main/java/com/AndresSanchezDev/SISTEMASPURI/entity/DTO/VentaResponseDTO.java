package com.AndresSanchezDev.SISTEMASPURI.entity.DTO;

import java.time.LocalDate;
import java.util.List;

public class VentaResponseDTO {
    private Long id;
    private LocalDate fecha;
    private Long vendedorId;
    private Long clienteId;
    private Boolean aCredito;

    private List<DetalleVentaResponseDTO> detalles;

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

    public Long getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<DetalleVentaResponseDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaResponseDTO> detalles) {
        this.detalles = detalles;
    }

    public Boolean getaCredito() {
        return aCredito;
    }

    public void setaCredito(Boolean aCredito) {
        this.aCredito = aCredito;
    }

}