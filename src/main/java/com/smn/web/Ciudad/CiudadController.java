package com.smn.web.Ciudad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class CiudadController {
	
	@Autowired
	private CiudadServiceImpl servicioCiudad;
	
	//METODO QUE ME DEVUELVE EL FORMULARIO PARA AGREGAR UNA NUEVA CIUDAD
	@GetMapping("/ciudad/nuevo")
	public String mostrarFomularioCiudad(Model modelo) {
		CiudadForm ciudadForm = new CiudadForm();
		modelo.addAttribute("ciudadForm", ciudadForm);
		return "crear_ciudad";
	}
	
	//METODO QUE ME PERMITE GUARDAR UNA NUEVA CIUDAD
	@PostMapping("/ciudad/agregar")
	public String guardarCiudad(@Valid @ModelAttribute("ciudadForm") CiudadForm ciudadForm, BindingResult result, Model modelo) {

		if (result.hasErrors()) 
		{
			modelo.addAttribute("ciudadForm", ciudadForm);
			System.out.println("Hubo errores");
			return "crear_ciudad";
		}
		try 
		{
			Ciudad ciudad = ciudadForm.toModel(); 
			servicioCiudad.guardarCiudad(ciudad);
			
			return "redirect:/clima/nuevo";
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
}
