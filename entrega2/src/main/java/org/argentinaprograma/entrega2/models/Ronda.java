package org.argentinaprograma.entrega2.models;

import java.util.List;

import org.argentinaprograma.entrega2.exceptions.IdPartidoNoEncontradoException;

import java.util.ArrayList;

public class Ronda {
	List<Partido> partidos;
	
	public Ronda(){
		this.partidos = new ArrayList<Partido>();
	}
	
	public void agregarPartidos(Partido partido) {
		this.partidos.add(partido);
	}
	
	public List<Partido> todosLosPartidos() {
		return this.partidos;
	}

	public Partido partido(int id_partido) 
			throws IdPartidoNoEncontradoException {
		
		Partido partido = null;
		for (Partido partidoJugado : partidos) {
			if(partidoJugado.esId(id_partido)) {
				partido = partidoJugado;
			}
		}
		if(partido == null) {
			throw new IdPartidoNoEncontradoException();
		}
		return partido;
	}
}
