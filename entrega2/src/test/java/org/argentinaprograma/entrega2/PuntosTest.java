package org.argentinaprograma.entrega2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.argentinaprograma.entrega2.models.Equipo;
import org.argentinaprograma.entrega2.models.Partido;
import org.argentinaprograma.entrega2.models.Ronda;
import org.argentinaprograma.entrega2.models.Pronostico;

import java.util.List;
import java.util.ArrayList;

public class PuntosTest {
	private Equipo equipoA;
	private Equipo equipoB;
	private Partido partido;
	private Partido otroPartido;
	private Ronda ronda;
	private Pronostico pronostico;
	private Pronostico otroPronostico;
	private String participante = "Pedro";
	private List<Pronostico> pronosticos = new ArrayList<Pronostico>();
	
	@Before
	public void inicializar() {
		equipoA = new Equipo("Argentina");
		equipoB = new Equipo("Arabia Saudita");
		
		partido = new Partido();
		partido.setId(1);
		partido.setRonda(1);
		partido.setEquipo1(equipoA);
		partido.setEquipo2(equipoB);
		partido.setGolesEquipo1(0);
		partido.setGolesEquipo2(0);
		
		otroPartido = new Partido();
		otroPartido.setId(2);
		partido.setRonda(2);
		otroPartido.setEquipo1(equipoA);
		otroPartido.setEquipo2(equipoB);
		otroPartido.setGolesEquipo1(0);
		otroPartido.setGolesEquipo2(0);
		
		ronda = new Ronda();
		
		pronostico = new Pronostico();
		pronostico.setParticipante(participante);
		pronostico.setId_partido(1);
		pronostico.setEquipoA(equipoA);
		pronostico.setGanaEquipoA("");
		pronostico.setEmpate("");
		pronostico.setGanaEquipoB("");
		
		otroPronostico = new Pronostico();
		otroPronostico.setParticipante(participante);
		otroPronostico.setId_partido(2);
		otroPronostico.setEquipoA(equipoA);
		otroPronostico.setGanaEquipoA("");
		otroPronostico.setEmpate("");
		otroPronostico.setGanaEquipoB("");
	}
	@Test
	public void SiParticipanteNoAciertaPronosticoNoObtienePuntos() {
		//Escenario
		partido.setGolesEquipo1(2);
		partido.setGolesEquipo2(0);
		ronda.agregarPartidos(partido);
		pronostico.setRonda(ronda);
		pronostico.setEmpate("x");
		otroPronostico.setRonda(ronda);
		otroPronostico.setGanaEquipoB("x");
		
		assertEquals(0, pronostico.puntos() + otroPronostico.puntos());
	}
	
	@Test
	public void SiParticipanteAciertaPronosticoObtieneUnPunto() {
		//Escenario
		partido.setGolesEquipo1(2);
		partido.setGolesEquipo2(0);
		ronda.agregarPartidos(partido);
		pronostico.setRonda(ronda);
		pronostico.setGanaEquipoA("x");

		assertEquals(1, pronostico.puntos());
	}
	
	@Test
	public void SiParticipanteAciertaDosVecesParaParaMismoPartidoDistintaRondaObtieneDosPuntos() {
		//Escenario
		partido.setGolesEquipo1(2);
		partido.setGolesEquipo2(0);
		ronda.agregarPartidos(partido);
		pronostico.setRonda(ronda);
		pronostico.setGanaEquipoA("x");
		pronosticos.add(pronostico);
		
		otroPartido.setGolesEquipo1(0);
		otroPartido.setGolesEquipo2(2);
		ronda.agregarPartidos(otroPartido);
		otroPronostico.setRonda(ronda);
		otroPronostico.setGanaEquipoB("x");
		pronosticos.add(otroPronostico);
		
		assertEquals(2, Ronda.puntosDe(participante, pronosticos));
	}
}
