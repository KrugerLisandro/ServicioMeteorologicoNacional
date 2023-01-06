package com.smn.web.Evento;

import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

		if (result.hasErrors()) {
			modelo.addAttribute("evento", eventoForm);
			System.out.println();

			System.out.println("Hubo errores");
			return "crear_evento";
		}

		try {
			Evento evento = eventoForm.toModel();
			servicioEvento.guardarEvento(evento);
			System.out.println("Se envía alerta por email a las siguienes casillas:");
			System.out.println(servicioEvento.emailPersonas(evento.getCiudad().getId()));

			return "redirect:/consultar_evento";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/";

	}

	@GetMapping("/consultar_evento/{id}")
	public String eliminarEvento(@PathVariable Long id, @ModelAttribute("evento") Evento evento, Model modelo) {
		try {
			Evento eventoExistente = servicioEvento.obtenerEventoId(id);
			servicioEvento.eliminarEvento(eventoExistente);
			return "redirect:/consultar_evento";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/";
	}

	public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	@ModelAttribute("allCiudades")
	public List<Ciudad> getAllCiudades() {
		return this.servicioCiudad.listarCiudades();
	}
}
