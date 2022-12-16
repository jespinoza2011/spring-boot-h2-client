package com.ibk.h2.api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private String nombre;
	private String apePaterno;
	private String apeMaterno;
	private String tipoDocumento;
	private String numeroDocumento;

	public Client(Long id, String codigo, String nombre, String apePaterno, String apeMaterno, String tipoDocumento,
			String numeroDocumento) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.apePaterno = apePaterno;
		this.apeMaterno = apeMaterno;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
	}

	public Client() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApePaterno() {
		return apePaterno;
	}

	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}

	public String getApeMaterno() {
		return apeMaterno;
	}

	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
}
