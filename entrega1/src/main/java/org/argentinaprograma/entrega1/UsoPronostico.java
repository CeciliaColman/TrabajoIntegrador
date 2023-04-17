package org.argentinaprograma.entrega1;

//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import org.argentinaprograma.entrega1.models.Equipo;
//import org.argentinaprograma.entrega1.models.Partido;
//import org.argentinaprograma.entrega1.models.ResultadoEnum;

import org.argentinaprograma.entrega1.models.ImportadorDatos;
import org.argentinaprograma.entrega1.models.Ronda;
import org.argentinaprograma.entrega1.models.Pronostico;

public class UsoPronostico {

	public static void main(String[] args) {

		if(args.length != 2) {
			System.out.println("Numero incorrecto de parametros.");
			System.exit(88);
		}

		//Path pathPronostico = Paths.get(args[1]);
		
		Ronda ronda = ImportadorDatos.crearRonda(args[0]);
		Pronostico[] pronosticos = ImportadorDatos.crearPronosticos(args[1], ronda);
		
		//Pronostico[] pronosticos = new Pronostico[2];
		//Equipo equipoA = null;
		/*try {
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
			e.printStackTrace();
		}
		try {
			String[] separados;
			int numeroPronostico = 0;
			ResultadoEnum resultadoParaEquipoA;
			Equipo equipo;
			
			for(String linea : Files.readAllLines(pathPronostico)) {
				separados = linea.split(",");
				System.out.print("Pronistico partido " + (numeroPronostico + 1) + "---> " );;
				if(separados[0].equals("x")) {
					System.out.println("Gana:" + ronda.partidosRonda(1)[numeroPronostico].getEquipo1().getNombre());
					resultadoParaEquipoA = ResultadoEnum.GANADOR;
				}else if(separados[1].equals("x")) {
					System.out.println("Empate");
					resultadoParaEquipoA = ResultadoEnum.EMPATE;
				}else {
					resultadoParaEquipoA = ResultadoEnum.PERDEDOR;
					System.out.println("Gana:" + ronda.partidosRonda(1)[numeroPronostico].getEquipo2().getNombre());
				}
				equipoA = ronda.partidosRonda(1)[numeroPronostico].getEquipo1();
				pronosticos[numeroPronostico] = new Pronostico(ronda.partidosRonda(1)[numeroPronostico], equipoA, resultadoParaEquipoA);
				numeroPronostico++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}*/

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
