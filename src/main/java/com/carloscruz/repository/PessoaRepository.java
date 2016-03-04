package com.carloscruz.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carloscruz.entity.Pessoa;


/**
 * @author carlos.duarte
 * Classe repositório para o objeto Pessoa   
 * @since 03/03/2016
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, String> {
	
	Pessoa findByIdePessoa(Integer idePessoa);
}
