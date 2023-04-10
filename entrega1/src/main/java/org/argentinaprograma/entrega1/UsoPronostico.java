package org.argentinaprograma.entrega1;

import org.argentinaprograma.entrega1.models.Equipo;
import org.argentinaprograma.entrega1.models.Partido;
import org.argentinaprograma.entrega1.models.ResultadoEnum;
import org.argentinaprograma.entrega1.models.Pronostico;

public class UsoPronostico {

	public static void main(String[] args) {
		Equipo equipoA = new Equipo("Argentina");
		Equipo equipoB = new Equipo("Paraguay");
		Partido partidoInicial = new Partido(equipoA, 1, equipoB, 0);
		
		Equipo equipoConsulta = new Equipo("Paraguay");
		ResultadoEnum resultado = new ResultadoEnum("g");
		
		Pronostico pronostico = new Pronostico(partidoInicial, equipoConsulta, resultado);
		
		System.out.println(pronostico.puntos());
	}

}
