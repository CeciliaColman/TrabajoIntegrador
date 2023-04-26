package org.argentinaprograma.entrega2.models;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.argentinaprograma.entrega2.models.Equipo;
import org.argentinaprograma.entrega2.models.Partido;
import org.argentinaprograma.entrega2.exceptions.IdPartidoNoEncontradoException;

import com.opencsv.bean.CsvToBeanBuilder;

public class ImportadorDatos {

	public static Ronda crearRonda(String rutaDelArchivoDeResultados) {

		String rutaArchivoConCamposCorrectos = verificarCamposArchivoResultados(rutaDelArchivoDeResultados);
		
		Ronda ronda = new Ronda();
	    List<Partido> lineasArchivoResultados = null;
        try {
            // En esta primera línea definimos el archivos que va a ingresar
        	lineasArchivoResultados = new CsvToBeanBuilder(new FileReader(rutaArchivoConCamposCorrectos))
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
		//eliminar(rutaArchivoConCamposCorrectos);
        return ronda;
	}

	private static void eliminar(String rutaArchivoConCamposCorrectos) {
		try {
			Files.delete(Paths.get(rutaArchivoConCamposCorrectos));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static String verificarCamposArchivoResultados(String rutaArchivoDeResultados) {
		
		Path pathArchivoOriginal = Paths.get(rutaArchivoDeResultados);
		System.out.println(pathArchivoOriginal);
		System.out.println(pathArchivoOriginal.toAbsolutePath());
		System.out.println(Files.exists(pathArchivoOriginal));
		try {
			System.out.println(Files.readAllLines(pathArchivoOriginal));
		} catch (IOException e) {
			System.out.println("ERROR AL PRIMER READALLLINES");
			e.printStackTrace();
		}
		String rutaArchivoResultadosCamposCorrectos = "TEMP-resultadosCamposCorrectos.csv";
		Path pathArchivoCamposCorrectos = Paths.get(rutaArchivoResultadosCamposCorrectos);
		
		System.out.println(pathArchivoCamposCorrectos);
		System.out.println(pathArchivoCamposCorrectos.toAbsolutePath());
		try {
			Files.deleteIfExists(pathArchivoCamposCorrectos);
			Files.createFile(pathArchivoCamposCorrectos);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		try {
			String[] separados;
			int golesEqA, golesEqB; 
			System.out.println("Por readalllines");
			System.out.println(Files.readAllLines(pathArchivoOriginal));
			for(String linea : Files.readAllLines(pathArchivoOriginal)) {
				System.out.println("Dentro de readalllines");
				separados = linea.split(",");
				if(separados.length == 6) {
					//Chequeo goles
					try {
						golesEqA = Integer.parseInt(separados[3]);
						golesEqB = Integer.parseInt(separados[4]);
						if((golesEqA >= 0) && (golesEqB >= 0)) {
							//agrego a archivo chequeado
							Files.write(pathArchivoCamposCorrectos, linea.getBytes(), StandardOpenOption.APPEND);
						}
					}catch(NumberFormatException e) {
						System.out.println("Error en linea.");
						//No agrego linea
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rutaArchivoResultadosCamposCorrectos;
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
