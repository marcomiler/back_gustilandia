package com.gustilandia.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustilandia.backend.dto.DTODetalleVenta;
import com.gustilandia.backend.dto.DTOTarjeta;
import com.gustilandia.backend.dto.DTOVentas;
import com.gustilandia.backend.model.Tarjeta;
import com.gustilandia.backend.model.Venta;
import com.gustilandia.backend.model.VentaDetalle;
import com.gustilandia.backend.repository.ProductoRepository;
import com.gustilandia.backend.repository.VentaRepository;
import com.gustilandia.backend.service.Response;
import com.gustilandia.backend.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService{
	
	@Autowired
	private VentaRepository ventarepo;
	
	@Autowired
	private ProductoRepository productorepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Response registrar(DTOVentas dtoVenta) {
		
		Response response = new Response();
		Venta venta = mappingDtoVenta(dtoVenta);
		try {
			
			double subtotal = 0.0;
			
			double dettotal = 0.0;
			
			for(VentaDetalle det:venta.getVentaDetalle()) {
				det.setVenta(venta);
				det.setProducto(productorepo.findById(det.getProducto().getIdProducto()).get());
				det.setPrecio(det.getProducto().getPrecio());
				dettotal = det.getPrecio() * det.getCantidad();
				subtotal += dettotal;
			}
			
			venta.setSubtotal(subtotal);
			venta.setIgv(subtotal * 0.18);
			venta.setTotal(venta.getSubtotal() + venta.getIgv());		
			
			venta = ventarepo.save(venta);
			
			response.setResult(venta);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setMessage("Hubo un error al registrar la venta: " + e.getMessage());
		}
		
		
		return response;
	}

	@Override
	public Response actualizar(DTOVentas dtoVenta) {
		Response response = new Response();
		Venta venta = mappingDtoVenta(dtoVenta);
		try {
			Optional<Venta> ventaOpt = ventarepo.findById(venta.getIdVenta());
			Venta _venta = ventaOpt.get();
			_venta.setNroVenta(venta.getNroVenta());
			_venta.setIdTipoComprobanteSunat(venta.getIdTipoComprobanteSunat());
			_venta.setCorrelativoComprobante(venta.getCorrelativoComprobante());
			_venta.setCliente(venta.getCliente());
			_venta.setTarjeta(venta.getTarjeta());
			_venta.setVentaDetalle(venta.getVentaDetalle());
			
			response.setResult(ventarepo.save(_venta));
			response.setSuccess(true);
			response.setMessage("Venta actualizada correctamente");
		} catch (Exception e) {
			response.setMessage("Hubo un error al actualizar la venta: " + e.getMessage());
		}
		return response;
	}

	@Override
	public Response eliminar(Long id) {
		// TODO Auto-generated method stub
		return new Response();
	}

	@Override
	public Response buscarId(Long id) {
		
		Venta venta = ventarepo.findById(id).get();
		if(venta.getIdEstado() != 1)
			return new Response(false, null, "La venta no existe.");
		
		return new Response(true, venta, "");
	}

	@Override
	public Response listar() {
		List<Venta> ventas = ventarepo.findAll().stream()
				.filter(venta -> venta.getIdEstado() == 1)
				.collect(Collectors.toList());
		return new Response(true, ventas, "");
	}
	
	public Venta mappingDtoVenta(DTOVentas dtoVenta) {
		
		Venta venta = mapper.map(dtoVenta, Venta.class);
		venta.setIdEstado(1L);
		venta.setFechaVentaGuardada(new Date(System.currentTimeMillis()));

		return venta;
		
	}
	
	public VentaDetalle mappginDtoDetalle(DTODetalleVenta dtoDetalle) {
		
		VentaDetalle detalle = mapper.map(dtoDetalle, VentaDetalle.class);
		return detalle;
		
	}
	
	public Tarjeta mappingDtoTarjeta(DTOTarjeta dtoTarjeta) {
		
		Tarjeta tarjeta = mapper.map(dtoTarjeta, Tarjeta.class);
		return tarjeta;
		
	}

}
