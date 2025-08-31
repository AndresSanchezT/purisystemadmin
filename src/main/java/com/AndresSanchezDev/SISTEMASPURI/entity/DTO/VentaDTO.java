package com.AndresSanchezDev.SISTEMASPURI.entity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class VentaDTO {
    private Long clienteId;
    private Long vendedorId;
    @JsonProperty("aCredito")
    private Boolean ACredito;
    private List<DetalleVentaDTO> detalles;

    public VentaDTO() {
    }
    public VentaDTO(Long clienteId, Long vendedorId, Boolean ACredito, List<DetalleVentaDTO> detalles) {
        this.clienteId = clienteId;
        this.vendedorId = vendedorId;
        this.ACredito = ACredito;
        this.detalles = detalles;
    }

    // Getters y Setters
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public Long getVendedorId() { return vendedorId; }
    public void setVendedorId(Long vendedorId) { this.vendedorId = vendedorId; }

    public List<DetalleVentaDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVentaDTO> detalles) { this.detalles = detalles; }

    public Boolean getACredito() {
        return ACredito;
    }

    public void setACredito(Boolean ACredito) {
        this.ACredito = ACredito;
    }
}