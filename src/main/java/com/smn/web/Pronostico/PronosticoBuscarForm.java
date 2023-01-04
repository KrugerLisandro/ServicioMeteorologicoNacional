package com.smn.web.Pronostico;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class PronosticoBuscarForm {
	
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date fechaActual;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date fechaExtendida;

	public PronosticoBuscarForm() {
		super();
	}

	public PronosticoBuscarForm(Date fechaActual, Date fechaExtendida) {
		super();
		this.fechaActual = fechaActual;
		this.fechaExtendida = fechaExtendida;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public Date getFechaExtendida() {
		return fechaExtendida;
	}

	public void setFechaExtendida(Date fechaExtendida) {
		this.fechaExtendida = fechaExtendida;
	}
	
}
		


