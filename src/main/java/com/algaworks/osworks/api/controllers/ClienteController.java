package com.algaworks.osworks.api.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.models.Cliente;


@RestController
public class ClienteController {
	
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Daniel Guerra");
		cliente1.setEmail("daniel@akgaworks.com.br");
		cliente1.setTelefone("11 99991-5472");
		
		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Thiago");
		cliente2.setEmail("thiago@algaworks.com.br");
		cliente2.setTelefone("11 99293-5353");
		
		
		return Arrays.asList(cliente1, cliente2);
		
	}
}


