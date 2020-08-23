package com.algaworks.osworks.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.models.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
