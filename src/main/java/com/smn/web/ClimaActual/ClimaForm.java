package com.smn.web.ClimaActual;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.smn.web.Ciudad.Ciudad;
import com.smn.web.EstadoClima.EstadoClima;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ClimaForm {

	private Long id;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fecha;

	@PositiveOrZero(message = "Humedad debe estar entre 0% a 100%")
	@Max(value = 100, message = "Humedad debe estar entre 0% a 100%")
	private int humedad;

	@PositiveOrZero(message = "Temperatura debe estar entre 0° a 60°")
	@Max(value = 60, message = "Temperatura debe estar entre 0° a 60°")
	private int temperatura;

	private Ciudad ciudad;

	@NotNull
	private EstadoClima estadoClima;

	public ClimaForm() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

	public int getHumedad() {
		return humedad;
	}

	public void setHumedad(int humedad) {
		this.humedad = humedad;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public EstadoClima getEstadoClima() {
		return estadoClima;
	}

	public void setEstadoClima(EstadoClima estadoClima) {
		this.estadoClima = estadoClima;
	}

	public Clima toModel() {
		
		LocalDate fechaActual = LocalDate.now(); // OBTENEMOS LA FECHA ACTUAL

		Clima newClima = new Clima();
		newClima.setFecha(fechaActual);
		newClima.setHumedad(this.humedad);
		newClima.setTemperatura(this.temperatura);
		newClima.setCiudad(this.ciudad);
		newClima.setEstadoClima(this.estadoClima);

		return newClima;
	}
}
