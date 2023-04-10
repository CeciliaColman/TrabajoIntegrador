package org.argentinaprograma.entrega1.models;

public class ResultadoEnum {
	
	private String resultado;
	private String ganador ="ganador";
	private String perdedor ="perdedor";
	private String empate ="empate";
	
	public ResultadoEnum(String resultadoEncuentro) {
		switch (resultadoEncuentro.toLowerCase()) {
		case "g":
			resultado = ganador;
			break;
		case "p":
			resultado = perdedor;
			break;
		case "e":
			resultado = empate;
			break;
		}
	}

	public String getResultado() {
		return resultado;
	}

}
