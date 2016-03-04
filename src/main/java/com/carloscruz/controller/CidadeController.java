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

import com.carloscruz.entity.Cidade;
import com.carloscruz.entity.Estado;
import com.carloscruz.service.CidadeService;


/**
 * @author carlos.duarte
 * Classe responsável por controlar as requisições ao serviços de cidade
 * @since 03/03/2016
 */
@RestController
@RequestMapping("cidade")
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public Iterable<Cidade> listar() throws Exception {
		return cidadeService.listarTodas();
	}
	
	@RequestMapping(value = "/listarComPaginacao", method = RequestMethod.GET)
	public Page<Cidade> listarComPaginacao(int page) throws Exception {
		return cidadeService.listarComPaginacao(new PageRequest(page, 5, Sort.Direction.ASC, "dscCidade"));
	}
	
	@RequestMapping(value = "/listarPorEstado", method = RequestMethod.GET)
	public Iterable<Cidade> listarPorEstado(int ideEstado) throws Exception {
		return cidadeService.listarPorEstado(new Estado(ideEstado));
	}
	
	@RequestMapping(value = "/selecionar", method = RequestMethod.GET)
	public Cidade selecionar(int ideCidade) throws Exception {
		return cidadeService.buscar(ideCidade);
	}
	
	@ResponseBody
    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public Cidade salvar(@Validated @RequestBody final Cidade cidade) throws Exception {
		return cidadeService.salvar(cidade);
    }
	
	@RequestMapping(value = "/excluir", method = RequestMethod.DELETE)
    public void excluir(int ideCidade) throws Exception {
		cidadeService.excluir(ideCidade);
    }

}
