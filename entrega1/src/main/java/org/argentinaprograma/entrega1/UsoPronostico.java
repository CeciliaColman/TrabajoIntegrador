package org.argentinaprograma.entrega1;

import org.argentinaprograma.entrega1.models.ImportadorDatos;
import org.argentinaprograma.entrega1.models.Ronda;
import org.argentinaprograma.entrega1.models.Pronostico;

public class UsoPronostico {

	public static void main(String[] args) {

		chequearCantidadArgumentos(args);

		Ronda ronda = ImportadorDatos.crearRonda(args[0]);
		Pronostico[] pronosticos = ImportadorDatos.crearPronosticos(args[1], ronda);
		
		System.out.println("TOTAL PUNTOS --> " + calcularPuntos(pronosticos));
		
	}

	private static void chequearCantidadArgumentos(String[] args) {
		if(args.length != 2) {
			System.out.println("Numero incorrecto de parametros.");
			System.exit(88);
		}
	}

	private static int calcularPuntos(Pronostico[] pronosticos) {
		int puntosTotales = 0;
		int nroPartido = 1;
		for(Pronostico pronostico : pronosticos) {
			puntosTotales += pronostico.puntos();
			System.out.println(pronostico.puntos() + " punto por partido " + nroPartido);
			nroPartido++;
		}
		return puntosTotales;
	}
}
