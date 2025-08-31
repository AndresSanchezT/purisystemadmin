package com.AndresSanchezDev.SISTEMASPURI.service;

import com.AndresSanchezDev.SISTEMASPURI.entity.DetalleVenta;
import com.AndresSanchezDev.SISTEMASPURI.entity.Venta;

import java.util.List;
import java.util.Optional;

public interface DetalleVentaService {
    List<DetalleVenta> findAll();
    Optional<DetalleVenta> findById(Long id);
    DetalleVenta save(DetalleVenta detalleVenta);
    void deleteById(Long id);
    void deleteByVenta(Venta venta);
}
