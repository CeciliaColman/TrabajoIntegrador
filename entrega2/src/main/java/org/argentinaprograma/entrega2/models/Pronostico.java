package org.argentinaprograma.entrega2.models;

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
		if(ronda.partido(id_partido).resultado(equipo).equals(resultado)) {
			return 1;
		}
		return 0;
	}

}
