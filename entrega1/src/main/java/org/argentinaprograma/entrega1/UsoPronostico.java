package org.argentinaprograma.entrega1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.argentinaprograma.entrega1.models.Equipo;
import org.argentinaprograma.entrega1.models.Partido;
import org.argentinaprograma.entrega1.models.ResultadoEnum;
import org.argentinaprograma.entrega1.models.Ronda;
import org.argentinaprograma.entrega1.models.Pronostico;

public class UsoPronostico {

	public static void main(String[] args) {
		//Creo el objeto "equipoA" y "equipoB"  de la Clase Equipo e inicalizo
		String archivoResultados = "src/main/resources/resultados.csv";
		Path pathResultados = Paths.get(archivoResultados);
		String archivoPronostico = "src/main/resources/pronostico.csv";
		Path pathPronostico = Paths.get(archivoPronostico);
		
		Ronda ronda = new Ronda(1);
		Pronostico[] pronosticos = new Pronostico[2];
		Equipo equipoA = null;
		Equipo equipoB = null;
		
		try {
			String[] separados;
			Partido[] partidos = new Partido[2];
			
			int numeroPartido = 0;
			int golesEquipoA, golesEquipoB;
			for(String linea : Files.readAllLines(pathResultados)) {
				separados = linea.split(",");
				equipoA = new Equipo(separados[0]);
				golesEquipoA = Integer.parseInt(separados[1]);
				golesEquipoB = Integer.parseInt(separados[2]);
				equipoB = new Equipo(separados[3]);
				partidos[numeroPartido] = new Partido(equipoA,
						golesEquipoA, equipoB, golesEquipoB);
				numeroPartido++;
			}
			ronda.agregarPartidos(partidos[0], partidos[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String[] separados;

			int numeroPronostico = 0;
			boolean ganaEquipoA = false;
			boolean ganaEquipoB = false;
			boolean empate = false;
			Equipo equipo;
			for(String linea : Files.readAllLines(pathPronostico)) {
				separados = linea.split(",");
				System.out.print("Pronistico partido " + (numeroPronostico + 1) + "---> " );;
				if(separados[0].equals("x")) {
					System.out.println("Gana:" + ronda.partidosRonda(1)[numeroPronostico].getEquipo1().getNombre());
					ganaEquipoA = true;
					equipo = ronda.partidosRonda(1)[numeroPronostico].getEquipo1();
					pronosticos[numeroPronostico] = new Pronostico(ronda.partidosRonda(1)[numeroPronostico], equipo, ResultadoEnum.GANADOR);
				}else if(separados[1].equals("x")) {
					System.out.println("Empate");
					empate = true;
					equipo = ronda.partidosRonda(1)[numeroPronostico].getEquipo1();
					pronosticos[numeroPronostico] = new Pronostico(ronda.partidosRonda(1)[numeroPronostico], equipo, ResultadoEnum.EMPATE);
				}else {
					System.out.println("Gana:" + ronda.partidosRonda(1)[numeroPronostico].getEquipo2().getNombre());
					ganaEquipoB = true;
					equipo = ronda.partidosRonda(1)[numeroPronostico].getEquipo2();
					pronosticos[numeroPronostico] = new Pronostico(ronda.partidosRonda(1)[numeroPronostico], equipo, ResultadoEnum.GANADOR);
				}
				numeroPronostico++;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Equipo equipoA = new Equipo("Argentina");
		//Equipo equipoB = new Equipo("Paraguay");
		
		//Creo el objeto "partidoInicial" de la Clase Partido y e inicalizo con
		//EquipoA, golesquipoA, equipoB, golesquipoA)
		//Partido partidoInicial = new Partido(equipoA, 1, equipoB, 1);
		
		//De aca en adelante no entiendo bien como funciona
		//------------------------------------------------------------------
		//Equipo equipoConsulta = new Equipo("Arabia Saudita"); //Equipo de prueba
		//ResultadoEnum resultado = ResultadoEnum.GANADOR; //resultado esperado de prueba
		
		//Se creo un Pronostico con los datos de prueba de arriba.
		//Partido partidoConsultado = ronda.partidosRonda(1)[0];
		//Pronostico pronostico = new Pronostico(partidoConsultado, equipoConsulta, resultado);
		//------------------------------------------------------------------		
		
		/*System.out.println(ronda.partidosRonda(1)[0].getEquipo1().getNombre());
		System.out.println(ronda.partidosRonda(1)[0].getGolesEquipo1());
		System.out.println(ronda.partidosRonda(1)[0].getEquipo2().getNombre());
		System.out.println(ronda.partidosRonda(1)[0].getGolesEquipo2());
		System.out.println("Pronostico: " + resultado);
		System.out.println("Puntos: " + pronostico.puntos());*/
		int puntosTotales = 0;
		int nroPartido = 1;
		for(Pronostico pronostico : pronosticos) {
			puntosTotales += pronostico.puntos();
			System.out.println(pronostico.puntos() + " punto por partido " + nroPartido);
			nroPartido++;
		}
		System.out.println("TOTAL PUNTOS --> " + puntosTotales);
	}

}
