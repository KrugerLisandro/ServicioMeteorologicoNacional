package com.smn.web.Evento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventoServiceImpl implements EventoService {
	
	@Autowired
	private EventoRepository repositorio;
	
	@Override
	public List<Evento> listarEventos() {
		return repositorio.findAll();
	}
	
	@Override
	public Evento obtenerEventoId(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public void guardarEvento(Evento evento) {
		repositorio.save(evento);
	}

	@Override
	public void actualizarEvento (Evento evento) {
		repositorio.save(evento);
	}

	@Override
	public void eliminarEvento(Evento evento) {
		repositorio.delete(evento);
	}
	
	//METODO QUE GENERA UNA LISTA DE EMAILS DE TODAS LAS PERSONAS QUE ESTEN ASOCIADAS A UNA CIUDAD
	@Override
	public List<String> emailPersonas(Long id_ciudad) {
		return repositorio.searchByCiudadQueryNative(id_ciudad);		 
	}

}
