package org.argentinaprograma.entrega1;

import org.argentinaprograma.entrega1.models.Equipo;
import org.argentinaprograma.entrega1.models.Partido;
import org.argentinaprograma.entrega1.models.ResultadoEnum;
import org.argentinaprograma.entrega1.models.ResultadoEnum;
import org.argentinaprograma.entrega1.models.Pronostico;

public class UsoPronostico {

	public static void main(String[] args) {
		//Creo el objeto "equipoA" y "equipoB"  de la Clase Equipo e inicalizo
		Equipo equipoA = new Equipo("Argentina");
		Equipo equipoB = new Equipo("Paraguay");
		
		//Creo el objeto "partidoInicial" de la Clase Partido y e inicalizo con
		//EquipoA, golesquipoA, equipoB, golesquipoA)
		Partido partidoInicial = new Partido(equipoA, 1, equipoB, 1);
		
		//De aca en adelante no entiendo bien como funciona
		//------------------------------------------------------------------
		Equipo equipoConsulta = new Equipo("Paraguay"); //Equipo de prueba
		ResultadoEnum resultado = ResultadoEnum.EMPATE; //resultado esperado de prueba
		
		//Se creo un Pronostico con los datos de prueba de arriba.
		Pronostico pronostico = new Pronostico(partidoInicial, equipoConsulta, resultado);
		//------------------------------------------------------------------		
		
		System.out.println(pronostico.puntos());
	}

}
