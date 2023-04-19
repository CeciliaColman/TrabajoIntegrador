package org.argentinaprograma.entrega2.models;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Partido {
	
	@CsvBindByPosition(position = 0)
	@Setter(AccessLevel.NONE)
	private int id;
    @CsvBindByPosition(position = 1)
    private int ronda;
    @CsvBindByPosition(position = 2)
    private Equipo equipo1;
    @CsvBindByPosition(position = 3)
    private int golesEquipo1;
    @CsvBindByPosition(position = 4)
    private int golesEquipo2;
    @CsvBindByPosition(position = 5)
    private Equipo equipo2;
    
	
	//Metodo para indicar si gano o perdió o empató
	public ResultadoEnum resultado(Equipo equipoParaConsultarResultado) {
		
		ResultadoEnum resultado = ResultadoEnum.EMPATE;
		
		if(equipoParaConsultarResultado.esEl(equipo1)) {
			if(this.golesEquipo1 > this.golesEquipo2) {
				resultado = ResultadoEnum.GANADOR;
			}else if(this.golesEquipo1 < this.golesEquipo2) {
				resultado = ResultadoEnum.PERDEDOR;
			}
		}else{
			if(this.golesEquipo2 > this.golesEquipo1) {
				resultado = ResultadoEnum.GANADOR;
			}else if(this.golesEquipo2 < this.golesEquipo1) {
				resultado = ResultadoEnum.PERDEDOR;
			}
		}
		return resultado;	
	}

	//Getters and Setters
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getRonda() {
		return ronda;
	}


	public void setRonda(int ronda) {
		this.ronda = ronda;
	}


	public Equipo getEquipo1() {
		return equipo1;
	}


	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}


	public int getGolesEquipo1() {
		return golesEquipo1;
	}


	public void setGolesEquipo1(int golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}


	public int getGolesEquipo2() {
		return golesEquipo2;
	}


	public void setGolesEquipo2(int golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}


	public Equipo getEquipo2() {
		return equipo2;
	}


	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
