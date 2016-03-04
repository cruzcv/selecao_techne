package com.carloscruz.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscruz.entity.Cidade;
import com.carloscruz.entity.Estado;
import com.carloscruz.repository.CidadeRepository;


/**
 * @author carlos.duarte
 * Classe responsável por toda regra de negócio da entidade Cidade   
 * @since 03/03/2016
 */
@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Transactional
	public Iterable<Cidade> listarTodas() throws Exception {
		return cidadeRepository.findAll();
	}
	
	@Transactional
	public Page<Cidade> listarComPaginacao(Pageable page) throws Exception {
		return cidadeRepository.findAll(page);
	}
	
	@Transactional
	public Iterable<Cidade> listarPorEstado(Estado estado) throws Exception {
		return cidadeRepository.findByIdeEstado(estado);
	}

	@Transactional
	public Cidade buscar(Integer ideCidade) throws Exception {
		return cidadeRepository.findByIdeCidade(ideCidade);
	}
	
	@Transactional
	public Cidade salvar(Cidade cidade) throws Exception {
		return cidadeRepository.save(cidade);
	}
	
	
	@Transactional
	public void excluir(int ideCidade) throws Exception {
		Cidade cidadeExcluir = buscar(ideCidade);
		cidadeRepository.delete(cidadeExcluir);
	}

}
