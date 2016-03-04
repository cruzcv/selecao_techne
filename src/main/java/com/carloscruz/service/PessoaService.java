package com.carloscruz.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carloscruz.entity.Pessoa;
import com.carloscruz.repository.PessoaRepository;

/**
 * @author carlos.duarte
 * Classe responsável por toda regra de negócio da entidade Pessoa   
 * @since 03/03/2016
 */
@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional
	public Iterable<Pessoa> listarTodas() throws Exception {
		return pessoaRepository.findAll();
	}
	
	@Transactional
	public Page<Pessoa> listarComPaginacao(Pageable page) throws Exception {
		return pessoaRepository.findAll(page);
	}
	
	@Transactional
	public Pessoa salvar(Pessoa pessoa) throws Exception {
		return pessoaRepository.save(pessoa);
	}

	@Transactional
	public Pessoa buscar(Integer idePessoa) throws Exception {
		return pessoaRepository.findByIdePessoa(idePessoa);
	}
	
	@Transactional
	public void excluir(int idePessoa) throws Exception {
		Pessoa pessoaExcluir = buscar(idePessoa);
		pessoaRepository.delete(pessoaExcluir);
	}

}
