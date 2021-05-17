package com.gustilandia.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustilandia.backend.dto.DTOCliente;
import com.gustilandia.backend.service.ClienteService;
import com.gustilandia.backend.service.Response;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping()
	public ResponseEntity<Response> listarUsuarios() {
		return new ResponseEntity<>(service.listar(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response> buscarId(@PathVariable("id") Long id) {
		
		Response cliente = service.buscarId(id);
		if(cliente == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(cliente, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registrarCliente(@RequestBody DTOCliente cliente) {
		return new ResponseEntity<>(service.registrar(cliente), HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> actualizarCliente(@RequestBody DTOCliente cliente) {
		
		Response _response = service.buscarId(cliente.getIdCliente());

		if(_response.getResult() == null)
			return new ResponseEntity<>(new Response(false, null, "El cliente no existe"),HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(service.actualizar(cliente), HttpStatus.OK);	
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> eliminarCliente(@PathVariable("id") Long id) {
		
		Response _response = service.buscarId(id);

		if(_response.getResult() == null)
			return new ResponseEntity<>(new Response(false, null, "El cliente no existe"),HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Response>(service.eliminar(id), HttpStatus.OK);
		
	}

}
