package org.argentinaprograma.entrega2.models;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.argentinaprograma.entrega2.exceptions.IdPartidoNoEncontradoException;

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

	public static List<Pronostico> crearPronosticos(String rutaDelArchivoDePronosticos, Ronda ronda){
		
		Path pathPronostico = Paths.get(rutaDelArchivoDePronosticos);
		List<Pronostico> pronosticos = new ArrayList<Pronostico>();
		
		try {
			String[] separados;
			//int numeroPartido = 1;
			int idPartido;
			ResultadoEnum resultadoParaEquipoA;
			Equipo equipoA = null;
			
			for(String linea : Files.readAllLines(pathPronostico)) {
				separados = linea.split(",");
				idPartido = Integer.parseInt(separados[0]);
				System.out.print("Pronistico partido " + idPartido + "---> " );
				if(separados[1].equals("x")) {
					try {
						System.out.println("Gana:" + ronda.partido(idPartido).getEquipo1().getNombre());
					} catch (IdPartidoNoEncontradoException e) {
						System.out.println("ID ERROR!");
					}
					resultadoParaEquipoA = ResultadoEnum.GANADOR;
				}else if(separados[2].equals("x")) {
					System.out.println("Empate");
					resultadoParaEquipoA = ResultadoEnum.EMPATE;
				}else {
					resultadoParaEquipoA = ResultadoEnum.PERDEDOR;
					try {
						System.out.println("Gana:" + ronda.partido(idPartido).getEquipo2().getNombre());
					} catch (IdPartidoNoEncontradoException e) {
						System.out.println("ID ERROR!");
					}
				}
				
				try {
					equipoA = ronda.partido(idPartido).getEquipo1();
					pronosticos.add(new Pronostico(ronda, idPartido, equipoA, resultadoParaEquipoA));
				} catch (IdPartidoNoEncontradoException e) {
					System.out.println("NO SE AGREDA PRONOSTICO PARA ID " + idPartido);
				}
				
				//pronosticos.add(new Pronostico(ronda, idPartido, equipoA, resultadoParaEquipoA));
			}
			
		} catch (IOException e) {
			System.out.println("Error IOException :" + e.getMessage());
		}
		
		return pronosticos;
	}

}
