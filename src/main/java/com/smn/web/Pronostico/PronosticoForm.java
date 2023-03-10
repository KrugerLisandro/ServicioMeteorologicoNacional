package com.smn.web.Pronostico;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.smn.web.Ciudad.Ciudad;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class PronosticoForm {

	private Long id;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Future(message = "La fecha solo puede ser futura")
	private Date fecha;

	@PositiveOrZero(message = "Probabilidad de lluvia debe estar entre 0% a 100%")
	@Max(value = 100, message = "Probabilidad de lluvia debe estar entre 0% a 100%" )
	private int probabilidad;

	@PositiveOrZero(message = "Cantidad de lluvia en mm debe ser 0 o superior")
	private int cantidad;

	@Size(min = 2, max = 250)
	private String descripcion;

	@NotNull
	private Ciudad ciudad;

	public PronosticoForm() {
		super();
	}

	public PronosticoForm(Date fecha, int probabilidad, int cantidad, String descripcion, Ciudad ciudad) {
		super();
		this.fecha = fecha;
		this.probabilidad = probabilidad;
		this.cantidad = cantidad;
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

	public int getProbabilidad() {
		return probabilidad;
	}

	public void setProbabilidad(int probabilidad) {
		this.probabilidad = probabilidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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

	public Pronostico toModel() {

		Pronostico newPronostico = new Pronostico();
		newPronostico.setFecha(this.getFecha());
		newPronostico.setProbabilidad(this.getProbabilidad());
		newPronostico.setCantidad(this.getCantidad());
		newPronostico.setDescripcion(this.getDescripcion());
		newPronostico.setCiudad(this.getCiudad());
		return newPronostico;
	}

}
