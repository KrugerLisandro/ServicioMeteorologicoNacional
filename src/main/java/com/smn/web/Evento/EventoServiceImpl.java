package com.smn.web.Evento;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smn.web.Excepcion.Excepcion;

@Service
public class EventoServiceImpl implements EventoService {

	@Autowired
	private EventoRepository repositorio;

	@Override
	public List<Evento> listarEventos() {
		return repositorio.findAll();
	}

	@Override
	public Evento obtenerEventoId(Long id) throws Exception {

		Evento obtenerEvento = this.repositorio.findById(id).get();

		if (obtenerEvento == null) {
			throw new Exception("No se pudo obtener el Evento");
		}

		return obtenerEvento;
	}

	@Override
	public void guardarEvento(Evento evento) throws Excepcion {

		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, -1);

		Calendar hoy = Calendar.getInstance();
		hoy.add(Calendar.DATE, 1);

		Date mañana = hoy.getTime();
		Date todayDate = today.getTime();

		// VALIDAMOS QUE LA FECHA NO SEA MAYOR A MAÑANA
		if (evento.getFecha().after(mañana) || evento.getFecha().before(todayDate)) {
			throw new Excepcion("La Fecha debe ser del Día de la Fecha o el Siguiente.");
		} else {
			repositorio.save(evento);
		}

	}

	@Override
	public void eliminarEvento(Evento evento) {
		repositorio.delete(evento);
	}

	// METODO QUE GENERA UNA LISTA DE EMAILS DE TODAS LAS PERSONAS QUE ESTEN
	// ASOCIADAS A UNA CIUDAD
	@Override
	public List<String> emailPersonas(Long id_ciudad) {
		return repositorio.searchByCiudadQueryNative(id_ciudad);
	}

}
