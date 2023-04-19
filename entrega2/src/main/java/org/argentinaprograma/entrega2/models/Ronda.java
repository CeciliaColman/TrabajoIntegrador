package org.argentinaprograma.entrega2.models;

import java.util.List;
import java.util.ArrayList;

public class Ronda {
	//private int rondaNumero;
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

	public Partido partido(int id_partido) {
		return partidos.get(id_partido - 1);
	}
}
