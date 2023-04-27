package org.argentinaprograma.entrega2.models;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

	public static void mostrarPuntuacionGeneral(List<Pronostico> pronosticos) {
		Map<String, Integer> puntosTodosLosParticipantes = new HashMap<String, Integer>();
		Map<String, Integer> pronosticosAcertados = new HashMap<String, Integer>();
		String participante;
		Integer puntosAcumulados;
		Integer aciertosAcumulados;
		for(Pronostico pronostico : pronosticos) {
			participante = pronostico.getParticipante();
			if(!puntosTodosLosParticipantes.containsKey(participante)) {
				puntosTodosLosParticipantes.put(participante, 0);
				pronosticosAcertados.put(participante, 0);
			}
			puntosAcumulados = puntosTodosLosParticipantes.get(participante);
			puntosTodosLosParticipantes.put(participante, puntosAcumulados + pronostico.puntos());
			
			if(pronostico.puntos() > 0) {
				aciertosAcumulados = pronosticosAcertados.get(participante);
				pronosticosAcertados.put(participante, aciertosAcumulados + 1);
			}
		}
		System.out.printf("%15s%23s%17s\n", "Participante", "Pronosticos acertados","Puntos Obtenidos");
		System.out.printf("%15s%23s%17s\n", "------------", "---------------------","----------------");
		for(String participo : puntosTodosLosParticipantes.keySet()) {
			System.out.printf("%15s%23d%17d\n", participo, pronosticosAcertados.get(participo),puntosTodosLosParticipantes.get(participo));
		}
	}
}
