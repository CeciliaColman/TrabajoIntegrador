package org.argentinaprograma.entrega1.models;

public class Ronda {
	private int rondaNumero;
	private Partido[] partidos = new Partido[2];
	
	public Ronda(int rondaNumero) {
		this.rondaNumero = rondaNumero;
	}
	public void agregarPartidos(Partido partidoA, Partido partidoB) {
		partidos[0] = partidoA;
		partidos[1] = partidoB;
	}
	public Partido[] partidosRonda(int ronda) {
		if(ronda == rondaNumero) {
			return partidos;
		}
		return null;
	}
}
