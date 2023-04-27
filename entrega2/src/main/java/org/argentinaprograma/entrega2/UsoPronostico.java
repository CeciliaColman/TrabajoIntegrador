package org.argentinaprograma.entrega2;

import java.util.List;
import org.argentinaprograma.entrega2.models.Ronda;
import org.argentinaprograma.entrega2.models.Pronostico;
import org.argentinaprograma.entrega2.models.ImportadorDatos;

public class UsoPronostico {

	public static void main(String[] args) {

		chequearCantidadArgumentos(args);

		Ronda ronda = ImportadorDatos.crearRonda(args[0]);
		List<Pronostico> pronosticos = ImportadorDatos.crearPronosticos(args[1], ronda);
		
		Ronda.mostrarPuntuacionGeneral(pronosticos);
		
	}

	private static void chequearCantidadArgumentos(String[] args) {
		if(args.length != 2) {
			System.out.println("Numero incorrecto de parametros.");
			System.exit(88);
		}
	}

}
