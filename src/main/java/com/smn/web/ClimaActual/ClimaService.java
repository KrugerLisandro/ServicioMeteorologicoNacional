package com.smn.web.ClimaActual;

import java.util.List;

import com.smn.web.Excepcion.Excepcion;

public interface ClimaService {
	
	List<Clima> listarClimas();

	Clima obtenerClimaId(Long id) throws Excepcion;
	
	void guardarClima(Clima clima) throws Excepcion;
	
	void actualizarClima(Clima clima);
	
	void eliminarClima(Clima clima);
}
