package org.argentinaprograma.entrega1.models;

public class Pronostico {
	private Partido partido;
	private Equipo equipo;
	private ResultadoEnum resultado;
	
	public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultado) {
		this.partido = partido;
		this.equipo = equipo;
		this.resultado = resultado;
	}
	
	public int puntos() {
		if(partido.resultado(equipo).getResultado().equals(resultado.getResultado())) {
			return 1;
		}
		return 0;
	}

}
