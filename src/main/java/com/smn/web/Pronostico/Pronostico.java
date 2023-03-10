package com.smn.web.Pronostico;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.smn.web.Ciudad.Ciudad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "pronostico")
public class Pronostico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Column
	private Date fecha;

	@Column
	private int probabilidad;

	@Column
	private int cantidad;

	@Column
	private String descripcion;

	@JoinColumn(name = "ciudad")
	@ManyToOne()
	private Ciudad ciudad;

	public Pronostico() {
		super();
	}

	public Pronostico(Date fecha, int probabilidad, int cantidad, String descripcion, Ciudad ciudad) {
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

}
