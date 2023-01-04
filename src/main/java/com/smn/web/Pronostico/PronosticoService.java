package com.smn.web.Pronostico;

import java.util.Date;
import java.util.List;

import com.smn.web.Ciudad.Ciudad;

public interface PronosticoService {
	
	List<Pronostico> listarPronosticos();
	
	Pronostico obtenerPronosticoId(Long id);
	
	void guardarPronostico(Pronostico pronostico);
	
	void actualizarPronostico(Pronostico pronostico);
	
	void eliminarPronostico(Pronostico pronostico);
	
	//List<Pronostico> filter(PronosticoBuscarForm pronosticoBuscarForm);
	
	List<Pronostico> listarPronosticosFecha(Date fecha);
	
	Pronostico obtenerPronosticoExiste (Ciudad ciudad, Date fecha);

}
