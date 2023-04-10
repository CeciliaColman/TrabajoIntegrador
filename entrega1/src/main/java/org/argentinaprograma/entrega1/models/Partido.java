package org.argentinaprograma.entrega1.models;

public class Partido {
	
	//Atributos
	private Equipo equipo1;
	private Equipo equipo2;
	private int golesEquipo1;
	private int golesEquipo2;
	
	//Constructor
	public Partido(Equipo equipo1, int golesEquipo1, Equipo equipo2, int golesEquipo2) {
		this.equipo1 = equipo1;
		this.equipo2= equipo2;
		this.golesEquipo1 = golesEquipo1;
		this.golesEquipo2 = golesEquipo2;
	}
	
	//Setters and Getters
	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}
	
	public void setGolesEquipo1(int golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}
	
	
	public int getGolesEquipo2() {
		return golesEquipo2;
	}

	public void setGolesEquipo2(int golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}
	
	//Cantidad de goles equipo 1
	public int getGolesEquipo1() {
		return golesEquipo1;
	}

	//Metodo para indicar si gano o perdió o empató
	public ResultadoEnum resultado(Equipo equipoParaConsultarResultado) {
		
		ResultadoEnum resultado = ResultadoEnum.EMPATE;
		
		if(equipoParaConsultarResultado.esEl(equipo1)) {
			if(this.golesEquipo1 > this.golesEquipo2) {
				resultado = ResultadoEnum.GANADOR;
			}else if(this.golesEquipo1 < this.golesEquipo2) {
				resultado = ResultadoEnum.PERDEDOR;
			}
		}else{
			if(this.golesEquipo2 > this.golesEquipo1) {
				resultado = ResultadoEnum.GANADOR;
			}else if(this.golesEquipo2 < this.golesEquipo1) {
				resultado = ResultadoEnum.PERDEDOR;
			}
		}
		return resultado;	
	}
}
