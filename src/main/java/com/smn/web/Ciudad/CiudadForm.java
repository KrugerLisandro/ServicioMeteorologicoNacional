package com.smn.web.Ciudad;

import jakarta.validation.constraints.Size;

/*
 *  LA CLASE FORM ME PERMITE GUARDAR LOS DATOS EN MI APLICACION WEB
 */

public class CiudadForm {

	private Long id;

	@Size(min = 4, max = 20, message = "Nombre Ciudad, debe tener entre 4 a 20 caracteres")
	private String nombre;

	public CiudadForm() {
		super();
	}

	public CiudadForm(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ciudad toModel() {

		Ciudad newCiudad = new Ciudad();
		newCiudad.setId(this.id);
		newCiudad.setNombre(this.nombre.toLowerCase());

		return newCiudad;
	}
}
