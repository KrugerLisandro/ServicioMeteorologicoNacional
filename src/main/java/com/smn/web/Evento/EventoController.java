package com.smn.web.Evento;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.smn.web.Ciudad.Ciudad;
import com.smn.web.Ciudad.CiudadServiceImpl;

import jakarta.validation.Valid;

@Controller
public class EventoController {

	@Autowired
	private EventoServiceImpl servicioEvento;

	@Autowired
	private CiudadServiceImpl servicioCiudad;

	@GetMapping("/consultar_evento")
	public String listado_evento(Model modelo) {
		modelo.addAttribute("listado_evento", servicioEvento.listarEventos());
		return "consultar_evento";
	}

	@GetMapping("/evento/nuevo")
	public String mostrarFomularioEvento(Model modelo) {
		Evento evento = new Evento();
		modelo.addAttribute("evento", evento);
		return "crear_evento";
	}

	@PostMapping("/evento/agregar")
	public String guardarEvento(@Valid @ModelAttribute("evento") EventoForm eventoForm, BindingResult result,
			Model modelo) {
		// servicioEvento.guardarEvento(evento);

		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, -1);

		Calendar hoy = Calendar.getInstance();
		hoy.add(Calendar.DATE, 1);

		Date mañana = hoy.getTime();
		Date todayDate = today.getTime();

		// VALIDAMOS QUE LA FECHA NO SEA MAYOR A MAÑANA
		if (eventoForm.getFecha().after(mañana) || eventoForm.getFecha().before(todayDate)) {
			FieldError error = new FieldError("evento", "fechaevento",
					"**La fecha debe ser del día de la fecha o el siguiente.");
			result.addError(error);
		}

		if (result.hasErrors()) {
			modelo.addAttribute("evento", eventoForm);
			System.out.println();

			System.out.println("Hubo errores");
			return "crear_evento";
		}

		Evento evento = eventoForm.toModel();
		evento.setCiudad(servicioCiudad.obtenerCiudadId(eventoForm.getId_ciudad()));
		servicioEvento.guardarEvento(evento);
		System.out.println("Se envía alerta por email a las siguienes casillas:");
		System.out.println(servicioEvento.emailPersonas(evento.getCiudad().getId()));

		return "redirect:/consultar_evento";

	}

	@GetMapping("/consultar_evento/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("evento", servicioEvento.obtenerEventoId(id));
		return "editar_evento";
	}

	@PostMapping("/consultar_evento/{id}")
	public String actualizarevento(@PathVariable Long id, @ModelAttribute("evento") Evento evento, Model modelo) {
		Evento eventoExistente = servicioEvento.obtenerEventoId(id);
		eventoExistente.setFecha(evento.getFecha());
		eventoExistente.setDescripcion(evento.getDescripcion());
		eventoExistente.setCiudad(evento.getCiudad());
		return "redirect:/consultar_evento";
	}

	@GetMapping("/consultar_evento/{id}")
	public String eliminarEvento(@PathVariable Long id, @ModelAttribute("evento") Evento evento, Model modelo) {
		Evento eventoExistente = servicioEvento.obtenerEventoId(id);
		servicioEvento.eliminarEvento(eventoExistente);
		return "redirect:/consultar_evento";
	}

	public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	@ModelAttribute("allCiudades")
	public List<Ciudad> getAllCiudades() {
		return this.servicioCiudad.listarCiudades();
	}
}
