package com.carloscruz.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pessoa", uniqueConstraints = @UniqueConstraint(columnNames = {"num_cpf"}, name = "uk_cpf"))
public class Pessoa implements Serializable {	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ide_pessoa")
	private Integer idePessoa;
	
	@Column(name = "nom_pesssoa")
	@NotNull(message = "erro.notnull.pessoa.nome")
	@Size(min = 1, max = 200, message = "erro.size.pessoa.nome")
	private String nomPessoa;
	
	@Column(name = "num_cpf")
	@Size(min = 11, max = 11, message = "erro.size.pessoa.cpf")	
	@NotNull(message = "erro.notnull.pessoa.cpf")
	private String numCpf;
	
	@Column(name = "dsc_email")
	@Size(min = 1, max = 50, message = "erro.size.pessoa.email")
	@NotNull(message = "erro.notnull.pessoa.email")
	private String dscEmail;
	
	@JoinColumn(name = "ide_cidade", referencedColumnName = "ide_cidade", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@NotNull(message = "erro.notnull.pessoa.cidade")
	private Cidade ideCidade;
	
	public Pessoa() {
		
	}

	public Integer getIdePessoa() {
		return idePessoa;
	}

	public void setIdePessoa(Integer idePessoa) {
		this.idePessoa = idePessoa;
	}

	public String getNomPessoa() {
		return nomPessoa;
	}

	public void setNomPessoa(String nomPessoa) {
		this.nomPessoa = nomPessoa;
	}

	public String getNumCpf() {
		return numCpf;
	}

	public void setNumCpf(String cpf) {
		this.numCpf = cpf;
	}

	public String getDscEmail() {
		return dscEmail;
	}

	public void setDscEmail(String dscEmail) {
		this.dscEmail = dscEmail;
	}

	public Cidade getIdeCidade() {
		return ideCidade;
	}

	public void setIdeCidade(Cidade ideCidade) {
		this.ideCidade = ideCidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idePessoa == null) ? 0 : idePessoa.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (idePessoa == null) {
			if (other.idePessoa != null)
				return false;
		} else if (!idePessoa.equals(other.idePessoa))
			return false;
		return true;
	}	
}
