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

import com.carloscruz.entity.Estado;
import com.carloscruz.service.EstadoService;

/**
 * @author carlos.duarte
 * Classe responsável por controlar as requisições ao serviços de estado
 * @since 03/03/2016
 */
@RestController
@RequestMapping("estado")
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	@ResponseBody
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public Iterable<Estado> listar() throws Exception {
		return estadoService.listarTodos();
	}
	
	@ResponseBody
	@RequestMapping(value = "/listarComPaginacao", method = RequestMethod.GET)
	public Page<Estado> listarComPaginacao(int page) throws Exception {
		return estadoService.listarComPaginacao(new PageRequest(page, 5, Sort.Direction.ASC, "dscEstado"));
	}
	
	@ResponseBody
	@RequestMapping(value = "/selecionar", method = RequestMethod.GET)
	public Estado selecionar(int ideEstado) throws Exception {
		return estadoService.buscar(ideEstado);
	}
	
	@ResponseBody
    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public Estado salvar(@Validated @RequestBody final Estado estado) throws Exception {
		return estadoService.salvar(estado);
    }
	
    @RequestMapping(value = "/excluir", method = RequestMethod.DELETE)
    public void excluir(int ideEstado) throws Exception {
		estadoService.excluir(ideEstado);
    }

}
