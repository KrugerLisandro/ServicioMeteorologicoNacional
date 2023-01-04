package com.smn.web.Evento;

import java.util.List;

public interface EventoService {
	
	List<Evento> listarEventos();
	
	Evento obtenerEventoId(Long id);
	
	void guardarEvento(Evento evento);
	
	void actualizarEvento(Evento evento);
	
	void eliminarEvento(Evento evento);

	List<String> emailPersonas(Long id_ciudad);
}
