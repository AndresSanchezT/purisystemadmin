package com.AndresSanchezDev.SISTEMASPURI.entity.DTO;

import java.time.LocalDate;
import java.util.List;

public record VentaResponseDTO2(Long id, LocalDate fecha, Boolean aCredito,
                               ClienteDTO cliente, List<DetalleVentaDTO2> detalles) {}
