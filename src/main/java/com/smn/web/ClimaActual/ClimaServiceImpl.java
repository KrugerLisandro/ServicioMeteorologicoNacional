package com.smn.web.ClimaActual;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClimaServiceImpl implements ClimaService{
	
	@Autowired
	private ClimaRepository repositorio;
	
	@Override
	public List<Clima> listarClimas() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

	@Override
	public Clima obtenerClimaId(Long id) {
		// TODO Auto-generated method stub
		return repositorio.findById(id).get();
	}

	@Override
	public void guardarClima(Clima clima) {
		// TODO Auto-generated method stub
		Boolean aux = false;
		List<Clima> climas = this.listarClimas();
		
		for (Clima c : climas) {
			if(c.getFecha().isEqual(clima.getFecha()) 
				&& c.getCiudad().getNombre().equals(clima.getCiudad().getNombre())) {
				aux = true;
			}
		}
		
		if(aux == false) {
			repositorio.save(clima);
			System.out.println("SAVE !");
		}else {
			System.out.println("El Clima ya Existe !");
		}
		
	}
	
	@Override
	public void actualizarClima(Clima clima) {
		// TODO Auto-generated method stub
		repositorio.save(clima);
	}

	@Override
	public void eliminarClima(Clima clima) {
		// TODO Auto-generated method stub
		repositorio.delete(clima);
	}

	

}
