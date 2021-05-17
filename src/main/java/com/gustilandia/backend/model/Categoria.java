package com.gustilandia.backend.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoria;
	
	@Column(name = "categoria")
	private String categoria;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_crea")
	private Date fechaCrea;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_crea", nullable = false)
	private Usuario usuarioCrea;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_edita")
	private Date FechaEdita;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_edita")
	private Usuario usuarioEdita;
	
	@Column(name = "id_estado")
	private int estado;
	
	@JsonIgnore
	@OneToMany(mappedBy = "categoria")
	private List<Producto> productos;

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getFechaCrea() {
		return fechaCrea;
	}

	public void setFechaCrea(Date fechaCrea) {
		this.fechaCrea = fechaCrea;
	}

	public Usuario getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(Usuario usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public Date getFechaEdita() {
		return FechaEdita;
	}

	public void setFechaEdita(Date fechaEdita) {
		FechaEdita = fechaEdita;
	}

	public Usuario getUsuarioEdita() {
		return usuarioEdita;
	}

	public void setUsuarioEdita(Usuario usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}


	

}
