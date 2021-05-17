package com.gustilandia.backend.dto;

import java.io.Serializable;

public class DTODetalleVenta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idProducto;
	private Double precio;
	private Double porcentajeDescuento;
	private int cantidad;
	
	public DTODetalleVenta() {
	}
	
	public DTODetalleVenta(Long idProducto, Double precio, Double porcentajeDescuento, int cantidad) {
		this.idProducto = idProducto;
		this.precio = precio;
		this.porcentajeDescuento = porcentajeDescuento;
		this.cantidad = cantidad;
	}
	
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	public void setPorcentajeDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
