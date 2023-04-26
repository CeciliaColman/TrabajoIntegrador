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
		
		List<Pronostico> lineasArchivoPronostico = new ArrayList<Pronostico>();
		
        try {
            // En esta primera línea definimos el archivos que va a ingresar
        	lineasArchivoPronostico = new CsvToBeanBuilder(new FileReader(rutaDelArchivoDePronosticos))
                    // con esta configuración podemos skipear la primera línea de nuestro archivo CSV
                    .withSkipLines(1)
                    // con esta configuración podemos elegir cual es el caracter que vamos a usar para delimitar
                    .withSeparator(',')
                    // Es necesario definir el tipo de dato que va a generar el objeto que estamos queriendo parsear a partir del CSV
                    .withType(Pronostico.class)
                    .build()
                    .parse();

        } catch (IOException e) {
        	e.printStackTrace();
        }
		
		return pronosticosSinErroresDeIndice(lineasArchivoPronostico, ronda);
	}

	private static List<Pronostico> pronosticosSinErroresDeIndice(List<Pronostico> lineasArchivoPronostico, Ronda ronda) {
		
		List<Integer> indicesConError = new ArrayList<>();
        Integer indiceActual = 0;
        
        for(Pronostico pronostico : lineasArchivoPronostico) {
        	try {
				pronostico.inicializarCon(ronda);
				//pronostico.mostrarPronostico();
			} catch (IdPartidoNoEncontradoException e) {
				indicesConError.add(indiceActual);
				System.out.println("ID<" + pronostico.idDelPartido() + ">ERROR! | No se cargara pronostico.");
			}
        	indiceActual++;
        }	

		for(int indiceConError : indicesConError) {
			lineasArchivoPronostico.remove(indiceConError);
		}
		
		return lineasArchivoPronostico;
	}

}
