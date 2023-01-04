package com.smn.web.Evento;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.smn.web.Ciudad.Ciudad;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class EventoForm {
	
	private Long id;

	//@FutureOrPresent(message = "VALIDACION Fecha solo puede ser el presente o futuro")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date fecha;
		
	@Size(min=2, max=500, message = "La descripción debe tener entre 2 y 500 caracteres")
	@NotNull
	private String descripcion;
	
	@NotNull
	private Ciudad ciudad;

	public EventoForm() {
		super();
	}

	public EventoForm(Date fecha, String descripcion, Ciudad ciudad) {
		super();
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
	}	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Ciudad getCiudad() {
		return ciudad;
	}


	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Evento toModel() {
		Evento newEvento = new Evento();
		newEvento.setFecha(this.getFecha());
		newEvento.setDescripcion(this.getDescripcion());
		newEvento.setCiudad(this.getCiudad());
		return newEvento;
	}
	
}
