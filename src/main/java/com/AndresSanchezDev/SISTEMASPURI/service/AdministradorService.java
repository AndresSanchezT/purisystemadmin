package com.AndresSanchezDev.SISTEMASPURI.service;

import com.AndresSanchezDev.SISTEMASPURI.entity.Administrador;

import java.util.List;
import java.util.Optional;

public interface AdministradorService {
    List<Administrador> findAll();
    Optional<Administrador> findById(Long id);
    Administrador save(Administrador administrador);
    void deleteById(Long id);
}
