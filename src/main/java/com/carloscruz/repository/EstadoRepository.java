package com.carloscruz.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carloscruz.entity.Estado;

/**
 * @author carlos.duarte
 * Classe repositório para o objeto Estado   
 * @since 03/03/2016
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, String> {

	@Query("SELECT e FROM Estado e WHERE Lower(e.dscEstado) like Lower(:dscEstado)")
	Page<Estado> findLikeByDscEstado(@Param("dscEstado") String dscEstado, Pageable page);	
	
	List<Estado> findByDscEstado(String dscEstado);
	
	Estado findByIdeEstado(Integer ideEstado);
}
