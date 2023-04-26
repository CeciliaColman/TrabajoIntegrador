package org.argentinaprograma.entrega2.models;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

//import org.argentinaprograma.entrega2.models.Equipo;
//import org.argentinaprograma.entrega2.models.Partido;
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
        return ronda;
	}

	private static String verificarCamposArchivoResultados(String rutaArchivoDeResultados) {
		
		Path pathArchivoOriginal = Paths.get(rutaArchivoDeResultados);

		String rutaArchivoResultadosCamposCorrectos = "TEMP-resultadosCamposCorrectos.csv";
		Path pathArchivoCamposCorrectos = Paths.get(rutaArchivoResultadosCamposCorrectos);
		
		try {
			Files.deleteIfExists(pathArchivoCamposCorrectos);
			Files.createFile(pathArchivoCamposCorrectos);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		try {
			String[] separados;
			String golesEqA, golesEqB;
			String regExNumeros = "[0-9]+";
			int nroLinea = 1;
			for(String linea : Files.readAllLines(pathArchivoOriginal)) {
				//Agrego "enter" al final de cada linea leida
				if(!(linea.endsWith("\n"))) {
					linea = linea + "\n";
				}
				
				separados = linea.split(",");
				if(nroLinea == 1) {
					//Agrego encabezado sin verificar
					Files.write(pathArchivoCamposCorrectos, linea.getBytes(), StandardOpenOption.APPEND);
				}else{
					if(separados.length == 6) {
						//Chequeo goles
						golesEqA = separados[3];
						golesEqB = separados[4];
						if((golesEqA.matches(regExNumeros)) && (golesEqB.matches(regExNumeros))) {
							//agrego a archivo chequeado
							Files.write(pathArchivoCamposCorrectos, linea.getBytes(), StandardOpenOption.APPEND);
						}else {
							System.out.println("Error \"goles\" en linea: " + nroLinea);
						}
					}else {
						System.out.println("Error cantidad de campos en linea: " + nroLinea);
					}
				}
				nroLinea++;
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
		
		return pronosticosSinErroresEnId_partido(lineasArchivoPronostico, ronda);
	}

	private static List<Pronostico> pronosticosSinErroresEnId_partido(List<Pronostico> lineasArchivoPronostico, Ronda ronda) {
		
		List<Pronostico> pronosticosConErrores = new ArrayList<Pronostico>();
        
        for(Pronostico pronostico : lineasArchivoPronostico) {
        	try {
				pronostico.inicializarCon(ronda);
				//pronostico.mostrarPronostico();
			} catch (IdPartidoNoEncontradoException e) {
				pronosticosConErrores.add(pronostico);
				System.out.println("No se encontro partido con ID<" + pronostico.idDelPartido() + "> | No se cargara pronostico.");
			}
        }	
        if(!pronosticosConErrores.isEmpty()) {
        	lineasArchivoPronostico.removeAll(pronosticosConErrores);
        }
		return lineasArchivoPronostico;
	}

}
