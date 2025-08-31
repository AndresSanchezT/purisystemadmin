package com.AndresSanchezDev.SISTEMASPURI.service;

import com.AndresSanchezDev.SISTEMASPURI.entity.Repartidor;

import java.util.List;
import java.util.Optional;

public interface RepartidorService {
    List<Repartidor> findAll();
    Optional<Repartidor> findById(Long id);
    Repartidor save(Repartidor repartidor);
    void deleteById(Long id);
}
