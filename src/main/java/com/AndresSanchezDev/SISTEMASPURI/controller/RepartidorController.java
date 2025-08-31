package com.AndresSanchezDev.SISTEMASPURI.controller;

import com.AndresSanchezDev.SISTEMASPURI.entity.Repartidor;
import com.AndresSanchezDev.SISTEMASPURI.service.RepartidorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/repartidores")
public class RepartidorController {

    private final RepartidorService repartidorService;

    public RepartidorController(RepartidorService repartidorService) {
        this.repartidorService = repartidorService;
    }

    @GetMapping
    public List<Repartidor> getAll() {
        return repartidorService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Repartidor> getById(@PathVariable Long id) {
        return repartidorService.findById(id);
    }

    @PostMapping
    public Repartidor create(@RequestBody Repartidor repartidor) {
        return repartidorService.save(repartidor);
    }

    @PutMapping("/{id}")
    public Repartidor update(@PathVariable Long id, @RequestBody Repartidor repartidor) {
        repartidor.setId(id);
        return repartidorService.save(repartidor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repartidorService.deleteById(id);
    }
}
