package org.argentinaprograma.entrega2.models;

import org.argentinaprograma.entrega2.exceptions.IdPartidoNoEncontradoException;

import com.opencsv.bean.CsvBindByPosition;

public class Pronostico {
	@CsvBindByPosition(position = 0)
	private int id_partido;
	@CsvBindByPosition(position = 1)
	private String ganaEquipoA;
	@CsvBindByPosition(position = 2)
	private String empate;
	@CsvBindByPosition(position = 3)
	private String ganaEquipoB;
	
	private Equipo equipoA;
	private Ronda ronda;
	
	public Pronostico() {
		
	}
	
	public int puntos() {
		try {
			if(ronda.partido(id_partido).resultado(equipoA).equals(resultadoEquipoA())) {
				return 1;
			}
		} catch (IdPartidoNoEncontradoException e) {
			System.out.print("Error, partido con id: " + id_partido + " no existe. | ");
		}
		return 0;
	}

	public int idDelPartido() {
		return id_partido;
	}

	public ResultadoEnum resultadoEquipoA() {
		if(ganaEquipoA.equalsIgnoreCase("x")) {
			return ResultadoEnum.GANADOR;
		}else if(ganaEquipoB.equalsIgnoreCase("x")) {
			return ResultadoEnum.PERDEDOR;
		}
		return ResultadoEnum.EMPATE;
	}

	public void mostrarPronostico() {
		System.out.print("Pronistico partido " + id_partido + "---> " );
		if(resultadoEquipoA() == ResultadoEnum.GANADOR) {
			System.out.println("Gana: " + equipoA.getNombre());
		}else if(resultadoEquipoA() == ResultadoEnum.PERDEDOR) {
			try {
				System.out.println("Gana: " + this.ronda.partido(id_partido).getEquipo2().getNombre());
			} catch (IdPartidoNoEncontradoException e) {
			}
		}else {
			System.out.println("EMPATE");
		}
	}

	public void inicializarCon(Ronda ronda) 
			throws IdPartidoNoEncontradoException {
		this.ronda = ronda;
		this.equipoA = this.ronda.partido(id_partido).getEquipo1();
	}

	public int getId_partido() {
		return id_partido;
	}

	public void setId_partido(int id_partido) {
		this.id_partido = id_partido;
	}

	public String getGanaEquipoA() {
		return ganaEquipoA;
	}

	public void setGanaEquipoA(String ganaEquipoA) {
		this.ganaEquipoA = ganaEquipoA;
	}

	public String getEmpate() {
		return empate;
	}

	public void setEmpate(String empate) {
		this.empate = empate;
	}

	public String getGanaEquipoB() {
		return ganaEquipoB;
	}

	public void setGanaEquipoB(String ganaEquipoB) {
		this.ganaEquipoB = ganaEquipoB;
	}

	public Equipo getEquipoA() {
		return equipoA;
	}

	public void setEquipoA(Equipo equipoA) {
		this.equipoA = equipoA;
	}

	public Ronda getRonda() {
		return ronda;
	}

	public void setRonda(Ronda ronda) {
		this.ronda = ronda;
	}

}
