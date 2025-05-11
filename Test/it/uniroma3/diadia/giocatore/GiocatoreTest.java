package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole;

class GiocatoreTest {

	private Partita partita;
	
	@BeforeEach
	public void setUp() throws Exception{
		IOConsole io=new IOConsole();
		this.partita=new Partita(io);
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
