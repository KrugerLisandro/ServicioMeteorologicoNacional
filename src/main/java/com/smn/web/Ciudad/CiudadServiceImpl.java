package com.smn.web.Ciudad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiudadServiceImpl implements CiudadService {

	@Autowired
	private CiudadRepository repositorio;

	@Override
	public List<Ciudad> listarCiudades() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public Ciudad obtenerCiudadId(Long id) {
		// TODO Auto-generated method stub
		return repositorio.findById(id).get();
	}

	@Override
	public void guardarCiudad(Ciudad ciudad) {
		// TODO Auto-generated method stub
		Boolean aux = false;
		List<Ciudad> ciudades = this.listarCiudades();

		for (Ciudad c : ciudades) {

			if (c.getNombre() == ciudad.getNombre()) {
				aux = true;
			}
		}

		if (aux == false) {
			repositorio.save(ciudad);
		} else {
			System.out.println("La Ciudad ya Existe !");
		}
	}
	
}
