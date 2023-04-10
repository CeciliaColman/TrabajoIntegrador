package org.argentinaprograma.entrega1.models;

public class ResultadoEnum {
	
	//Atributos
	private String resultado;
	private String ganador ="ganador";
	private String perdedor ="perdedor";
	private String empate ="empate";
	
	//Metodo que recibe una caracter (g, p, e) y guarda en resultado 
	//el String "ganador", ""perdedor", "empato"
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
    
	//Metodo para devolver resultado
	public String getResultado() {
		return resultado;
	}

}
