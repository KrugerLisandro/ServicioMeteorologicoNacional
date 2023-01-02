package com.smn.web.Ciudad;

import java.util.List;

public interface CiudadService {
	
	List<Ciudad> listarCiudades();

	Ciudad obtenerCiudadId(Long id);
	
	void guardarCiudad(Ciudad ciudad);
	
	void eliminarCiudad(Ciudad ciudad);
}
