package it.uniroma3.diadia;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
	private Partita partita;
	
	@BeforeEach
	public void setUp() throws Exception{
		this.partita=new Partita();
	}

	@Test
	public void testNuovaPartitaNotFinita() {
		assertFalse(this.partita.isFinita());
	}
	
	@Test
	public void testNuovaPartitaVinta() {
		partita.getLabirinto().setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	public void testNuovaPartitaPersaCfu() {
		partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testNuovaPartitaNotFinita1() {
		assertFalse(this.partita.isFinita());
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	public void testVintaFalse() {
		assertFalse(partita.vinta());
	}
	
	@Test
	public void testVintaTrue() {
		partita.getLabirinto().setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		assertTrue(partita.vinta());
	}
	
	@Test
	public void testVintaFalse1() {
		Stanza s1=new Stanza("Biblioteca");
		partita.getLabirinto().setStanzaCorrente(s1);
		assertFalse(partita.vinta());
	}
	
	@Test
	public void testIsFinitaFalse() {
		assertFalse(partita.isFinita());
	}
	
	@Test
	public void testIsFinitaTrue() {
		partita.getLabirinto().setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		assertTrue(partita.isFinita());
	}
	
	@Test
	public void testIsFinitaCfu() {
		partita.getGiocatore().setCfu(0);
		assertTrue(partita.isFinita());
	}
}
