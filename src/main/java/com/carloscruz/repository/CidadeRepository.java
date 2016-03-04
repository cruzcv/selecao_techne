package com.carloscruz.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carloscruz.entity.Cidade;
import com.carloscruz.entity.Estado;

/**
 * @author carlos.duarte
 * Classe repositório para o objeto Cidade   
 * @since 03/03/2016
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, String> {

	Cidade findByIdeCidade(Integer ideCidade);
	
	List<Cidade> findByIdeEstado(Estado estado);

}
