package org.argentinaprograma.entrega2.models;

import org.argentinaprograma.entrega2.exceptions.IdPartidoNoEncontradoException;

public class Pronostico {
	private int id_partido;
	private Equipo equipo;
	private ResultadoEnum resultado;
	private Ronda ronda;
	
	public Pronostico(Ronda ronda, int id_partido, Equipo equipo, ResultadoEnum resultado) {
		this.ronda = ronda;
		this.id_partido = id_partido;
		this.equipo = equipo;
		this.resultado = resultado;
	}
	
	public int puntos() {
		try {
			if(ronda.partido(id_partido).resultado(equipo).equals(resultado)) {
				return 1;
			}
		} catch (IdPartidoNoEncontradoException e) {
			System.out.println("Error, partido con id: " + id_partido + " no existe.");
		}
		return 0;
	}

	public int idDelPartido() {
		return id_partido;
	}

}
