package com.AndresSanchezDev.SISTEMASPURI.controller;

import com.AndresSanchezDev.SISTEMASPURI.entity.*;
import com.AndresSanchezDev.SISTEMASPURI.entity.DTO.DetalleVentaDTO;
import com.AndresSanchezDev.SISTEMASPURI.entity.DTO.DetalleVentaResponseDTO;
import com.AndresSanchezDev.SISTEMASPURI.entity.DTO.VentaDTO;
import com.AndresSanchezDev.SISTEMASPURI.entity.DTO.VentaResponseDTO;
import com.AndresSanchezDev.SISTEMASPURI.service.*;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final ClienteService clienteService;
    private final VendedorService vendedorService;
    private final ProductoService productoService;
    private final DetalleVentaService detalleVentaService;

    public VentaController(VentaService ventaService,
                           ClienteService clienteService,
                           VendedorService vendedorService,
                           ProductoService productoService,
                           DetalleVentaService detalleVentaService) {
        this.ventaService = ventaService;
        this.clienteService = clienteService;
        this.vendedorService = vendedorService;
        this.productoService = productoService;
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public List<VentaResponseDTO> listarVentas() {
        List<Venta> ventas = ventaService.findAll();
        List<VentaResponseDTO> response = new ArrayList<>();

        for (Venta v : ventas) {
            VentaResponseDTO dto = new VentaResponseDTO();
            dto.setId(v.getId());
            dto.setFecha(v.getFecha());
            dto.setVendedorId(v.getVendedor().getId());
            dto.setClienteId(v.getCliente().getId());

            setDetallesDTO(v, dto);
            response.add(dto);
        }

        return response;
    }

    @PostMapping("/crear")
    public VentaResponseDTO crearVenta(@RequestBody VentaDTO ventaDTO) {
        // Buscar cliente y vendedor
        Cliente cliente = clienteService.findById(ventaDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Vendedor vendedor = vendedorService.findById(ventaDTO.getVendedorId())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        // Actualizar cliente si tiene crédito
        if(cliente.getTieneCredito() && !ventaDTO.getACredito()){
            cliente.setTieneCredito(true);
        } else {
            cliente.setTieneCredito(ventaDTO.getACredito());
        }
        clienteService.save(cliente);

        // Crear venta
        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setVendedor(vendedor);
        venta.setFecha(LocalDate.now());
        venta.setaCredito(ventaDTO.getACredito());
        venta = ventaService.save(venta);

        // Crear detalles
        List<DetalleVenta> detalles;
        venta.setDetalles(detallesReturn(ventaDTO, venta));
        venta = ventaService.save(venta);

        // Mapear a DTO
        VentaResponseDTO responseDTO = new VentaResponseDTO();
        responseDTO.setId(venta.getId());
        responseDTO.setFecha(venta.getFecha());
        responseDTO.setVendedorId(venta.getVendedor().getId());
        responseDTO.setClienteId(venta.getCliente().getId());
        responseDTO.setaCredito(venta.getaCredito());

        setDetallesDTO(venta, responseDTO);

        return responseDTO;
    }

    private void setDetallesDTO(Venta venta, VentaResponseDTO responseDTO) {
        List<DetalleVentaResponseDTO> detallesDTO = new ArrayList<>();
        for (DetalleVenta d : venta.getDetalles()) {
            DetalleVentaResponseDTO ddto = new DetalleVentaResponseDTO();
            ddto.setId(d.getId());
            ddto.setCantidad(d.getCantidad());
            ddto.setProductoId(d.getProducto().getId());
            detallesDTO.add(ddto);
        }
        responseDTO.setDetalles(detallesDTO);
    }

    private List<DetalleVenta> detallesReturn(@RequestBody VentaDTO ventaDTO, Venta venta) {
        List<DetalleVenta> detalles = new ArrayList<>();
        for (DetalleVentaDTO detalleDTO : ventaDTO.getDetalles()) {
            Producto producto = productoService.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle = detalleVentaService.save(detalle);
            detalles.add(detalle);
        }
        return detalles;
    }


    private Venta getVenta(@RequestBody VentaDTO ventaDTO, Venta venta) {
        detallesReturn(ventaDTO, venta);
        venta.setaCredito(ventaDTO.getACredito());
        venta.setDetalles(detallesReturn(ventaDTO, venta));
        return ventaService.save(venta);
    }

    @PutMapping("/actualizar/{id}")
    @Transactional
    public Venta actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
        Venta venta = ventaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        // Actualizar cliente y vendedor si es necesario
        Cliente cliente = clienteService.findById(ventaDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Vendedor vendedor = vendedorService.findById(ventaDTO.getVendedorId())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        venta.setCliente(cliente);
        venta.setVendedor(vendedor);
        venta.setFecha(LocalDate.now()); // actualizar fecha si quieres

        // Eliminar detalles anteriores
        detalleVentaService.deleteByVenta(venta);

        // Crear nuevos detalles
        return getVenta(ventaDTO, venta);
    }

    // ================== ELIMINAR VENTA ==================
    @DeleteMapping("/eliminar/{id}")
    @Transactional
    public void eliminarVenta(@PathVariable Long id) {
        Venta venta = ventaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        // Eliminar detalles primero
        detalleVentaService.deleteByVenta(venta);

        // Eliminar la venta
        ventaService.deleteById(id);
    }
}
