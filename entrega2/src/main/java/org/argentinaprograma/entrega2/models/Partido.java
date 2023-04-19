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
}
