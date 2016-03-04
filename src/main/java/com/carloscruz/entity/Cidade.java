package com.carloscruz.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cidade", uniqueConstraints = {@UniqueConstraint(columnNames = {"dsc_cidade", "ide_estado"}, name = "uk_dsc_cidade_ide_estado")})
public class Cidade implements Serializable {	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ide_cidade")
	private Integer ideCidade;
	
	@Column(name = "dsc_cidade")
	@NotNull(message = "erro.notnull.cidade.descricao")
	@Size(min = 1, max = 50, message = "erro.size.cidade.descricao")
	private String dscCidade;
	
	@JoinColumn(name = "ide_estado", referencedColumnName = "ide_estado", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@NotNull(message = "erro.notnull.cidade.estado")
	private Estado ideEstado;
	
	@OneToMany(mappedBy = "ideCidade", fetch = FetchType.LAZY)
	private Collection<Pessoa> pessoaCollection;
	
	public Cidade() {
		
	}

	public Integer getIdeCidade() {
		return ideCidade;
	}

	public void setIdeCidade(Integer ideCidade) {
		this.ideCidade = ideCidade;
	}

	public String getDscCidade() {
		return dscCidade;
	}

	public void setDscCidade(String dscCidade) {
		this.dscCidade = dscCidade;
	}

	public Estado getIdeEstado() {
		return ideEstado;
	}

	public void setIdeEstado(Estado ideEstado) {
		this.ideEstado = ideEstado;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ideCidade == null) ? 0 : ideCidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (ideCidade == null) {
			if (other.ideCidade != null)
				return false;
		} else if (!ideCidade.equals(other.ideCidade))
			return false;
		return true;
	}
}
