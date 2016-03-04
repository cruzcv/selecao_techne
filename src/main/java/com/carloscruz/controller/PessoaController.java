package com.carloscruz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.carloscruz.entity.Pessoa;
import com.carloscruz.service.PessoaService;


/**
 * @author carlos.duarte
 * Classe responsável por controlar as requisições ao serviços de pessoa
 * @since 03/03/2016
 */
@RestController
@RequestMapping("pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public Iterable<Pessoa> listar() throws Exception {
		return pessoaService.listarTodas();
	}
	
	@RequestMapping(value = "/listarComPaginacao", method = RequestMethod.GET)
	public Page<Pessoa> listarComPaginacao(int page) throws Exception {
		return pessoaService.listarComPaginacao(new PageRequest(page, 5, Sort.Direction.ASC, "nomPessoa"));
	}
	
	@RequestMapping(value = "/selecionar", method = RequestMethod.GET)
	public Pessoa selecionar(int idePessoa) throws Exception {
		return pessoaService.buscar(idePessoa);
	}
	
	@ResponseBody
    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public Pessoa salvar(@Validated @RequestBody final Pessoa pessoa) throws Exception {
		return pessoaService.salvar(pessoa);
    }
	
	@RequestMapping(value = "/excluir", method = RequestMethod.DELETE)
    public void excluir(int idePessoa) throws Exception {
		pessoaService.excluir(idePessoa);
    }

}
