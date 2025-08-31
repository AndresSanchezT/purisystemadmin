package com.AndresSanchezDev.SISTEMASPURI.service;

import com.AndresSanchezDev.SISTEMASPURI.entity.Repartidor;
import com.AndresSanchezDev.SISTEMASPURI.repository.RepartidorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepartidorServiceImpl implements RepartidorService {

    private final RepartidorRepository repartidorRepository;

    public RepartidorServiceImpl(RepartidorRepository repartidorRepository) {
        this.repartidorRepository = repartidorRepository;
    }

    @Override
    public List<Repartidor> findAll() {
        return repartidorRepository.findAll();
    }

    @Override
    public Optional<Repartidor> findById(Long id) {
        return repartidorRepository.findById(id);
    }

    @Override
    public Repartidor save(Repartidor repartidor) {
        return repartidorRepository.save(repartidor);
    }

    @Override
    public void deleteById(Long id) {
        repartidorRepository.deleteById(id);
    }
}