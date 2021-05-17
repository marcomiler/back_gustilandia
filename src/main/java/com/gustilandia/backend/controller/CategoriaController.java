package com.gustilandia.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustilandia.backend.model.Categoria;
import com.gustilandia.backend.model.Producto;
import com.gustilandia.backend.model.UnidadMedida;
import com.gustilandia.backend.service.CategoriaService;
import com.gustilandia.backend.service.Response;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public ResponseEntity<Response> listarCategoria() {
		return new ResponseEntity<>(service.listar(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarId(@PathVariable("id") Long id) {
		
		Response cat = service.buscarId(id);
		if(cat == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(cat, HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}/productos")
	public ResponseEntity<Response> buscarProductosCategoria(@PathVariable("id") Long id) {
		
		Response cat = service.buscarId(id);
		if(cat == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(cat, HttpStatus.OK);
		
	}
	

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registrarCategoria(@RequestBody Categoria cat) {
		return new ResponseEntity<>(service.registrar(cat), HttpStatus.OK);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> actualizarId(@RequestBody Categoria categoria) {
		
		Response _cat = service.buscarId(categoria.getIdCategoria());
		if(_cat == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(service.actualizar(categoria), HttpStatus.OK);
		
	}
	
	

}
