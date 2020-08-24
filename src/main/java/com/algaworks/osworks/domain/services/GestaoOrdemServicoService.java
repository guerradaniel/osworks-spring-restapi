package com.algaworks.osworks.domain.services;

import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.models.OrdemServico;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	
	private OrdemServicoRepository ordemServicoRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		
		return ordemServicoRepository.save(ordemServico);
	}
}
