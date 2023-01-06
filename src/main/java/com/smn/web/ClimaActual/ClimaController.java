package com.smn.web.ClimaActual;

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
import com.smn.web.EstadoClima.EstadoClima;
import com.smn.web.EstadoClima.EstadoClimaServiceImpl;
import com.smn.web.Excepcion.Excepcion;

import jakarta.validation.Valid;

@Controller
public class ClimaController {

	@Autowired
	private ClimaServiceImpl servicioClima;

	@Autowired
	private CiudadServiceImpl servicioCiudad;

	@Autowired
	private EstadoClimaServiceImpl servicioEstadoClima;

	// ME PERMITE DEVOLVER LA LISTA DE LOS CLIMAS GUARDADOS
	@GetMapping("/consultar_clima")
	public String listado_clima(Model modelo) {
		modelo.addAttribute("listado_clima", servicioClima.listarClimas());
		return "consultar_clima";
	}

	// ME DEVUELVE EL FORMULARIO PARA REGISTRAR UN CLIMA
	@GetMapping("/clima/nuevo")
	public String mostrarFomularioClima(Model modelo) {
		ClimaForm climaForm = new ClimaForm();
		modelo.addAttribute("climaForm", climaForm);
		return "crear_clima";
	}

	// GUARDAR LOS DATOS DEL FORMULARIO DEL CLIMA
	@PostMapping("/clima/agregar")
	public String guardarCiudad(@Valid @ModelAttribute("climaForm") ClimaForm climaForm, BindingResult result,
			Model modelo) {

		if (result.hasErrors()) {
			modelo.addAttribute("climaForm", climaForm);
			System.out.println("Hubo errores");
			return "crear_clima";
		}

		try {
			Clima clima = climaForm.toModel();
			servicioClima.guardarClima(clima);
			return "redirect:/consultar_clima";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/";
	}

	// DEVULVE EL FORMULARIO PARA EDITAR UN CLIMA
	@GetMapping("/consultar_clima/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model modelo) throws Excepcion {
		modelo.addAttribute("climaForm", servicioClima.obtenerClimaId(id));
		return "editar_clima";
	}

	// GUARDAR LOS DATOS EDITADOS DEL CLIMA
	@PostMapping("/consultar_clima/{id}")
	public String actualizarClima(@PathVariable Long id, @Valid @ModelAttribute("climaForm") ClimaForm climaForm,
			BindingResult result, Model modelo) throws Excepcion {
		Clima climaExistente = servicioClima.obtenerClimaId(id);

		if (result.hasErrors()) {
			modelo.addAttribute("climaForm", climaForm);
			System.out.println("Hubo errores");
			return "editar_clima";
		}

		try {
			climaExistente.setHumedad(climaForm.getHumedad());
			climaExistente.setTemperatura(climaForm.getTemperatura());
			climaExistente.setCiudad(climaForm.getCiudad());
			climaExistente.setEstadoClima(climaForm.getEstadoClima());

			servicioClima.actualizarClima(climaExistente);

			return "redirect:/consultar_clima";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/";
	}

	// ELIMINAR UN CLIMA
	@GetMapping("/consultar_clima/{id}")
	public String eliminarClima(@PathVariable Long id, @ModelAttribute("clima") Clima clima, Model modelo) {

		try {
			Clima climaExistente = servicioClima.obtenerClimaId(id);
			servicioClima.eliminarClima(climaExistente);
			return "redirect:/consultar_clima";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/";
	}

	// LISTA DE CIUDADES REGISTRADAS
	@ModelAttribute("allCiudades")
	public List<Ciudad> getAllCiudades() {
		return this.servicioCiudad.listarCiudades();
	}

	// LISTA DE ESTADOS DEL CLIMA REGISTRADOS
	@ModelAttribute("allEstadoClimas")
	public List<EstadoClima> getAllEstadoClimas() {
		return this.servicioEstadoClima.listarEstadosClima();
	}

}
