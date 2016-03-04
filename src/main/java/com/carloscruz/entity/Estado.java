package com.carloscruz.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "estado", uniqueConstraints = {@UniqueConstraint(columnNames = {"dsc_estado"}, name = "uk_dsc_estado"), 
		@UniqueConstraint(columnNames = {"sgl_estado"}, name = "uk_sgl_estado")})
public class Estado implements Serializable {	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ide_estado")
	private Integer ideEstado;
	
	@Column(name = "dsc_estado")
	@NotNull(message = "erro.notnull.estado.descricao")
	@Size(min = 1, max = 50, message = "erro.size.estado.descricao")
	private String dscEstado;
	
	@Column(name = "sgl_estado")
	@NotNull(message = "erro.notnull.estado.sigla")
	@Size(min = 2, max = 2, message = "erro.size.estado.sigla")
	private String sglEstado;
	
	@OneToMany(mappedBy = "ideEstado", fetch = FetchType.LAZY)
	private Collection<Cidade> cidadeCollection;
	
	public Estado() {
		
	}
	
	public Estado(Integer ideEstado, String dscEstado) {
		this.ideEstado = ideEstado;
		this.dscEstado = dscEstado;
	}

	public Estado(Integer ideEstado) {
		this.ideEstado = ideEstado;
	}

	public Integer getIdeEstado() {
		return ideEstado;
	}

	public void setIdeEstado(Integer ideEstado) {
		this.ideEstado = ideEstado;
	}

	public String getDscEstado() {
		return dscEstado;
	}

	public void setDscEstado(String dscEstado) {
		this.dscEstado = dscEstado;
	}

	public String getSglEstado() {
		return sglEstado;
	}

	public void setSglEstado(String sglEstado) {
		this.sglEstado = sglEstado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ideEstado == null) ? 0 : ideEstado.hashCode());
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
		Estado other = (Estado) obj;
		if (ideEstado == null) {
			if (other.ideEstado != null)
				return false;
		} else if (!ideEstado.equals(other.ideEstado))
			return false;
		return true;
	}
}
