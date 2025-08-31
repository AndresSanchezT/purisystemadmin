package com.AndresSanchezDev.SISTEMASPURI.repository;

import com.AndresSanchezDev.SISTEMASPURI.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    @Query("SELECT COUNT(v) FROM Venta v WHERE v.cliente.id = :clienteId AND v.aCredito = true")
    int contarVentasACredito(@Param("clienteId") Long clienteId);

    @Query("SELECT v FROM Venta v " +
            "JOIN FETCH v.detalles d " +
            "JOIN FETCH d.producto " +
            "JOIN FETCH v.cliente " +
            "WHERE v.id = :id")
    Venta findVentaConDetallesYProductos(@Param("id") Long id);

    @Query("SELECT DISTINCT v FROM Venta v " +
            "JOIN FETCH v.detalles d " +
            "JOIN FETCH d.producto " +
            "JOIN FETCH v.cliente")
    List<Venta> findAllConDetallesYProductos();
}
