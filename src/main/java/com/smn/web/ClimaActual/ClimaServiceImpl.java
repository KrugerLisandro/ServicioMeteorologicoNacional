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
		repositorio.save(clima);
	}

	@Override
	public void eliminarClima(Clima clima) {
		// TODO Auto-generated method stub
		repositorio.delete(clima);
	}

}
