package com.smn.web.Pronostico;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smn.web.Ciudad.Ciudad;
import com.smn.web.Excepcion.Excepcion;

@Service
public class PronosticoServiceImpl implements PronosticoService {

	@Autowired
	private PronosticoRepository repositorio;

	@Override
	public List<Pronostico> listarPronosticos() {
		return repositorio.findAll();
	}

	@Override
	public Pronostico obtenerPronosticoId(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public void guardarPronostico(Pronostico pronostico) throws Excepcion {

		Pronostico pronosticoExiste = this.obtenerPronosticoExiste(pronostico.getCiudad(), pronostico.getFecha());

		if (pronosticoExiste != null) {
			throw new Excepcion ("Pronostico repetido");
		} else {
			System.out.println("Pronostico guardado");
			repositorio.save(pronostico);
		}
	}

	@Override
	public void actualizarPronostico(Pronostico pronostico) {
		repositorio.save(pronostico);
	}

	@Override
	public void eliminarPronostico(Pronostico pronostico) {
		repositorio.delete(pronostico);
	}

	/*
	 * @Override public List<Pronostico> filter(PronosticoBuscarForm
	 * pronosticoBuscarForm) { return
	 * repositorio.findByFilter(pronosticoBuscarForm.getId(),pronosticoBuscarForm.
	 * getFechaActual(),pronosticoBuscarForm.getFechaExtendida()); }
	 */

	@Override
	public List<Pronostico> listarPronosticosFecha(Date fecha) {
		return repositorio.findByFecha(fecha);
	}

	@Override
	public Pronostico obtenerPronosticoExiste(Ciudad ciudad, Date fecha) {

		return repositorio.findByCiudadAndFecha(ciudad, fecha);
	}

}
