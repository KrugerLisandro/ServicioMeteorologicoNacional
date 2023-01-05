package com.smn.web.Pronostico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class PronosticoController {

	@Autowired
	private PronosticoServiceImpl servicioPronostico;

	@Autowired
	private CiudadServiceImpl servicioCiudad;

	@GetMapping("/consultar_pronostico")
	public String listado_pronostico(Model modelo) throws ParseException {
		// modelo.addAttribute("listado_pronostico",
		// servicioPronostico.listarPronosticos());
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String fechaComoCadena = formato.format(new Date());

		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaComoCadena);

		modelo.addAttribute("listado_pronostico", servicioPronostico.listarPronosticosFecha(fecha));
		return "consultar_pronostico";
	}

	@GetMapping("/pronostico/nuevo")
	public String mostrarFomularioPronostico(Model modelo) {
		PronosticoForm pronosticoForm = new PronosticoForm();
		modelo.addAttribute("pronosticoForm", pronosticoForm);
		return "crear_pronostico";
	}

	@PostMapping("/pronostico/agregar")
	public String guardarPronostico(@Valid PronosticoForm pronosticoForm, BindingResult result, Model modelo) {

		if (result.hasErrors()) {
			modelo.addAttribute("pronosticoForm", pronosticoForm);
			System.out.println("Hubo errores");
			return "crear_pronostico";
		}

		try {
			Pronostico pronostico = pronosticoForm.toModel();
			servicioPronostico.guardarPronostico(pronostico);
			return "redirect:/consultar_pronostico";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/";
	}

	@GetMapping("/consultar_pronostico/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("pronosticoFormEditar", servicioPronostico.obtenerPronosticoId(id));
		return "editar_pronostico";
	}

	@PostMapping("/consultar_pronostico/{id}")
	public String actualizarPronostico(@PathVariable Long id,
			@Valid @ModelAttribute("pronosticoFormEditar") PronosticoFormEditar pronosticoFormEditar, BindingResult result,
			Model modelo) {
		Pronostico pronosticoExistente = servicioPronostico.obtenerPronosticoId(id);

		if (result.hasErrors()) {
			modelo.addAttribute("pronosticoFormEditar", pronosticoFormEditar);
			System.out.println("Hubo errores");
			return "editar_pronostico";
		}

		try {
			pronosticoExistente.setFecha(pronosticoFormEditar.getFecha());
			pronosticoExistente.setProbabilidad(pronosticoFormEditar.getProbabilidad());
			pronosticoExistente.setCantidad(pronosticoFormEditar.getCantidad());
			pronosticoExistente.setDescripcion(pronosticoFormEditar.getDescripcion());
			pronosticoExistente.setCiudad(pronosticoFormEditar.getCiudad());
			servicioPronostico.actualizarPronostico(pronosticoExistente);

			return "redirect:/consultar_pronostico";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/";
	}

	@ModelAttribute("allCiudades")
	public List<Ciudad> getAllCiudades() {
		return this.servicioCiudad.listarCiudades();
	}
}
