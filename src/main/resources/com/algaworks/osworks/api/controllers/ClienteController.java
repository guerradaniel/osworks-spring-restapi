package com.algaworks.osworks.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
	
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		
	}
}


