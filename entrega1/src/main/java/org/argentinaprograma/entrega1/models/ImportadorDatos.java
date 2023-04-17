package org.argentinaprograma.entrega1.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImportadorDatos {

	public static Ronda crearRonda(String rutaDelArchivoDeResultados) {

		Ronda ronda = new Ronda(1);
		
		Path pathResultados = Paths.get(rutaDelArchivoDeResultados);
		try {
			String[] separados;
			Partido[] partidos = new Partido[2];
			
			int numeroPartido = 0;
			int golesEquipoA, golesEquipoB;
			for(String linea : Files.readAllLines(pathResultados)) {
				separados = linea.split(",");
				Equipo equipoA = new Equipo(separados[0]);
				golesEquipoA = Integer.parseInt(separados[1]);
				golesEquipoB = Integer.parseInt(separados[2]);
				Equipo equipoB = new Equipo(separados[3]);
				partidos[numeroPartido] = new Partido(equipoA,
						golesEquipoA, equipoB, golesEquipoB);
				numeroPartido++;
			}
			ronda.agregarPartidos(partidos[0], partidos[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ronda;
	}

	public static Pronostico[] crearPronosticos(String rutaDelArchivoDePronosticos, Ronda ronda) {
		Path pathPronostico = Paths.get(rutaDelArchivoDePronosticos);
		Pronostico[] pronosticos = new Pronostico[2];
		
		try {
			String[] separados;
			int numeroPronostico = 0;
			ResultadoEnum resultadoParaEquipoA;
			Equipo equipoA;
			
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
		}
		
		return pronosticos;
	}

}
