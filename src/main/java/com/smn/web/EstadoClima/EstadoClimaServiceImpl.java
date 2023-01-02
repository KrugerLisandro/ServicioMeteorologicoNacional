package com.smn.web.EstadoClima;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoClimaServiceImpl implements EstadoClimaService {
	
	@Autowired
	private EstadoClimaRepository repositorio;
	
	//UNA VEZ REALIZADA LA PRIMERA COMPILACION ESTE METODO DEBE SER COMENTADO
		@Override
		public void EstadosClimaInicial() {
			// TODO Auto-generated method stub
			EstadoClima estado = new EstadoClima("Despejado");
			EstadoClima estado2 = new EstadoClima("Nublado");
			EstadoClima estado3 = new EstadoClima("Lluvioso");
			EstadoClima estado4 = new EstadoClima("Nevando");
			
			repositorio.save(estado);
			repositorio.save(estado2);
			repositorio.save(estado3);
			repositorio.save(estado4);
		}
	
	@Override
	public List<EstadoClima> listarEstadosClima() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}

}
