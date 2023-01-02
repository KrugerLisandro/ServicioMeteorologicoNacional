package com.smn.web.EstadoClima;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoClimaServiceImpl implements EstadoClimaService {
	
	@Autowired
	private EstadoClimaRepository repositorio;
	
	@Override
	public List<EstadoClima> listarEstadosClima() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

}
