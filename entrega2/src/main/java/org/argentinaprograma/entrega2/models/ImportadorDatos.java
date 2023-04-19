package org.argentinaprograma.entrega2.models;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

public class ImportadorDatos {

	public static Ronda crearRonda(String rutaDelArchivoDeResultados) {

		Ronda ronda = new Ronda();
	    List<Partido> lineasArchivoResultados = null;
        try {
            // En esta primera línea definimos el archivos que va a ingresar
        	lineasArchivoResultados = new CsvToBeanBuilder(new FileReader(rutaDelArchivoDeResultados))
                    // con esta configuración podemos skipear la primera línea de nuestro archivo CSV
                    .withSkipLines(1)
                    // con esta configuración podemos elegir cual es el caracter que vamos a usar para delimitar
                    .withSeparator(',')
                    // Es necesario definir el tipo de dato que va a generar el objeto que estamos queriendo parsear a partir del CSV
                    .withType(Partido.class)
                    .build()
                    .parse();

        } catch (IOException e) {
        	e.printStackTrace();
        }
        for(Partido partido : lineasArchivoResultados) {
        	partido.inicializarEquipos();
        	ronda.agregarPartidos(partido);
        }
		return ronda;
	}

	public static List<Pronostico> crearPronosticos(String rutaDelArchivoDePronosticos, Ronda ronda) {
		
		Path pathPronostico = Paths.get(rutaDelArchivoDePronosticos);
		List<Pronostico> pronosticos = new ArrayList<Pronostico>();
		
		try {
			String[] separados;
			int numeroPartido = 1;
			ResultadoEnum resultadoParaEquipoA;
			Equipo equipoA;
			
			for(String linea : Files.readAllLines(pathPronostico)) {
				separados = linea.split(",");
				System.out.print("Pronistico partido " + numeroPartido + "---> " );
				if(separados[1].equals("x")) {
					System.out.println("Gana:" + ronda.partido(numeroPartido).getEquipo1().getNombre());
					resultadoParaEquipoA = ResultadoEnum.GANADOR;
				}else if(separados[2].equals("x")) {
					System.out.println("Empate");
					resultadoParaEquipoA = ResultadoEnum.EMPATE;
				}else {
					resultadoParaEquipoA = ResultadoEnum.PERDEDOR;
					System.out.println("Gana:" + ronda.partido(numeroPartido).getEquipo2().getNombre());
				}
				equipoA = ronda.partido(numeroPartido).getEquipo1();
				pronosticos.add(new Pronostico(ronda, numeroPartido, equipoA, resultadoParaEquipoA));
				numeroPartido++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return pronosticos;
	}

}
