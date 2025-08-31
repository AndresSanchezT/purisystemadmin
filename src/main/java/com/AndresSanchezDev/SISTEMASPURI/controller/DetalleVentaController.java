package com.AndresSanchezDev.SISTEMASPURI.controller;

import com.AndresSanchezDev.SISTEMASPURI.entity.DetalleVenta;
import com.AndresSanchezDev.SISTEMASPURI.service.DetalleVentaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalles-venta")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public List<DetalleVenta> getAll() {
        return detalleVentaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<DetalleVenta> getById(@PathVariable Long id) {
        return detalleVentaService.findById(id);
    }

    @PostMapping
    public DetalleVenta create(@RequestBody DetalleVenta detalleVenta) {
        return detalleVentaService.save(detalleVenta);
    }

    @PutMapping("/{id}")
    public DetalleVenta update(@PathVariable Long id, @RequestBody DetalleVenta detalleVenta) {
        detalleVenta.setId(id);
        return detalleVentaService.save(detalleVenta);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        detalleVentaService.deleteById(id);
    }
}
