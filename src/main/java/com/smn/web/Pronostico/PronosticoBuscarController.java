package com.smn.web.Pronostico;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smn.web.Ciudad.Ciudad;
import com.smn.web.Ciudad.CiudadServiceImpl;

@Controller
@RequestMapping("/pronosticoBuscar")
public class PronosticoBuscarController {

	@Autowired
	private PronosticoServiceImpl servicioPronostico;
	@Autowired
	private CiudadServiceImpl servicioCiudad;

	@RequestMapping(method = RequestMethod.GET)
	public String preparaForm(Model modelo) {
		PronosticoBuscarForm pronosticoBuscarForm = new PronosticoBuscarForm();
		pronosticoBuscarForm.setId(1L);
		modelo.addAttribute("formBean", pronosticoBuscarForm);
		return "pronosticoBuscar";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute("formBean") @Validated PronosticoBuscarForm pronosticoBuscarForm,
			BindingResult result, ModelMap modelo, @RequestParam String action) {

		if (action.equals("Buscar")) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fechaComoCadena = sdf.format(new Date());
				Date fechaactual = new SimpleDateFormat("yyyy-MM-dd").parse(fechaComoCadena);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(fechaactual);
				calendar.add(Calendar.DAY_OF_YEAR, 10);
				var fechaext = calendar.getTime();
				String fechaCadena = sdf.format(fechaext);
				Date fechaextendida = new SimpleDateFormat("yyyy-MM-dd").parse(fechaCadena);

				pronosticoBuscarForm.setId(pronosticoBuscarForm.getId());
				pronosticoBuscarForm.setFechaActual(fechaactual);
				pronosticoBuscarForm.setFechaExtendida(fechaextendida);

				List<Pronostico> pronostico = servicioPronostico.filter(pronosticoBuscarForm);
				modelo.addAttribute("resultados", pronostico);

			} catch (Exception e) {
				ObjectError error = new ObjectError("globalError", e.getMessage());
				result.addError(error);

			}
			modelo.addAttribute("formBean", pronosticoBuscarForm);
			return "pronosticoBuscar";
		}

		if (action.equals("Cancelar")) {
			modelo.clear();
			return "redirect:/";
		}

		if (action.equals("Evento")) {
			return "redirect:/consultar_evento/{id}";
		}

		return "redirect:/";
	}

	@ModelAttribute("allCiudades")
	public List<Ciudad> getAllCiudades() {
		return this.servicioCiudad.listarCiudades();
	}

}
