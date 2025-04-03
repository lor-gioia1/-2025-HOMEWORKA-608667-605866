package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Partita;

class GiocatoreTest {

	private Partita partita;
	
	@BeforeEach
	public void setUp() throws Exception{
		this.partita=new Partita();
	}
	
	@Test
	void testGetCfuIniziali() {
		assertEquals(20,partita.getGiocatore().getCfu());
	}
	
	@Test
	void testGetCfuMenoUno() {
		partita.getGiocatore().removeCfu();
		assertEquals(19,partita.getGiocatore().getCfu());
	}
	
	@Test
	void testGetCfuFiniti() {
		partita.getGiocatore().setCfu(0);
		assertTrue(partita.isFinita());
		assertEquals(0,partita.getGiocatore().getCfu());
	}

}
