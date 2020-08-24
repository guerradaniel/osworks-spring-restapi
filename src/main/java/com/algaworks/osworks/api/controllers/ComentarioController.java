package com.algaworks.osworks.api.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.api.models.Comentario;
import com.algaworks.osworks.api.models.ComentarioInput;
import com.algaworks.osworks.api.models.ComentarioModel;
import com.algaworks.osworks.domain.services.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@Autowired
	private ModelMapper modelMapper;
	
	 public ComentarioModel adicionar(@PathVariable Long ordemServicoId,
			 @Valid @RequestBody ComentarioInput comentarioInput) {
		 Comentario comentario = gestaoOrdemServico.adicionarComentario(ordemServicoId, 
				 comentarioInput.getDescricao());
		 
		 return toModel(comentario);
	 }
	 
	 private ComentarioModel toModel(Comentario comentario) {
		 return modelMapper.map(comentario, ComentarioModel.class);
	 }
}

