package com.smn.web.ClimaActual;

import java.util.List;

public interface ClimaService {
	
	List<Clima> listarClimas();

	Clima obtenerClimaId(Long id);
	
	void guardarClima(Clima clima);
	
	void actualizarClima(Clima clima);
	
	void eliminarClima(Clima clima);
}
