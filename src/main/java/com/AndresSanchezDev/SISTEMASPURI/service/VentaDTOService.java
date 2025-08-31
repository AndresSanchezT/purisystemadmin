package com.AndresSanchezDev.SISTEMASPURI.service;

import com.AndresSanchezDev.SISTEMASPURI.entity.Cliente;
import com.AndresSanchezDev.SISTEMASPURI.entity.DTO.*;
import com.AndresSanchezDev.SISTEMASPURI.entity.Venta;
import com.AndresSanchezDev.SISTEMASPURI.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaDTOService {

    private final VentaRepository ventaRepository;

    public VentaDTOService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public VentaResponseDTO2 getVentaDTO(Long id) {
        Venta venta = ventaRepository.findVentaConDetallesYProductos(id);
        return mapToDTO(venta);
    }

    public List<VentaResponseDTO2> getTodasLasVentasDTO() {
        return ventaRepository.findAllConDetallesYProductos()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private VentaResponseDTO2 mapToDTO(Venta venta) {
        Cliente c = venta.getCliente();
        ClienteDTO clienteDTO = new ClienteDTO(c.getId(), c.getNombre(), c.getTieneCredito());

        List<DetalleVentaDTO2> detallesDTO = venta.getDetalles()
                .stream()
                .map(d -> new DetalleVentaDTO2(
                        d.getId(),
                        d.getCantidad(),
                        new ProductoDTO(d.getProducto().getId(), d.getProducto().getNombre(), d.getProducto().getPrecio())
                ))
                .collect(Collectors.toList());

        return new VentaResponseDTO2(venta.getId(), venta.getFecha(), venta.getaCredito(), clienteDTO, detallesDTO);
    }
}