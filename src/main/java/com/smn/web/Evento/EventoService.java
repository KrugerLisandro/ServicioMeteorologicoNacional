package com.smn.web.Evento;

import java.util.List;

import com.smn.web.Excepcion.Excepcion;

public interface EventoService {
	
	List<Evento> listarEventos();
	
	Evento obtenerEventoId(Long id) throws Exception;
	
	void guardarEvento(Evento evento) throws Excepcion;
	
	void eliminarEvento(Evento evento);

	List<String> emailPersonas(Long id_ciudad);
}
