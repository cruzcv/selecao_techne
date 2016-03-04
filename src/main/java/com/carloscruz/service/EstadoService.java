package com.carloscruz.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscruz.entity.Estado;
import com.carloscruz.repository.EstadoRepository;


/**
 * @author carlos.duarte
 * Classe responsável por toda regra de negócio da entidade Estado   
 * @since 03/03/2016
 */
@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Transactional
	public Iterable<Estado> listarTodos() throws Exception {
		return estadoRepository.findAll();
	}
	
	@Transactional
	public Page<Estado> listarComPaginacao(Pageable page) throws Exception {
		//return estadoRepository.findLikeByDscEstado("%" + dscEstado + "%", page);
		return estadoRepository.findAll(page);
	}
	
	@Transactional
	public Estado salvar(Estado estado) throws Exception {
		return estadoRepository.save(estado);
	}

	@Transactional
	public Estado buscar(Integer ideEstado) throws Exception {
		return estadoRepository.findByIdeEstado(ideEstado);
	}
	
	@Transactional
	public void excluir(int ideEstado) throws Exception {
		Estado estadoExcluir = buscar(ideEstado);
		estadoRepository.delete(estadoExcluir);
	}

}
