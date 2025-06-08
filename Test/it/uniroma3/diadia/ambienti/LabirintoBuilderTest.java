package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole;

class LabirintoBuilderTest {

	private Labirinto labirinto;
	private Partita partita;
	
	@BeforeEach
	public void setUp() throws Exception{
		this.labirinto=new Labirinto.LabirintoBuilder()
				.addStanzaVincente("camera")
				.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAdiacenza("salotto", "cucina", "nord")
				.addAttrezzo("osso", 1)
				.getLabirinto();
		this.partita=new Partita(labirinto, new IOConsole());
	}
	
	@Test
	public void testAddStanzaVincente() {
		assertEquals("camera",partita.getLabirinto().getStanzaVincente().getNome());
	}
	
	@Test
	public void testAddStanzaIniziale() {
		assertEquals("salotto",partita.getLabirinto().getStanzaCorrente().getNome());
	}
	
	@Test
	public void testAddAdiacenza() {
		assertEquals("cucina",partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente("nord").getNome());
	}

	@Test
	public void testAddAttrezzo() {
		assertTrue(partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente("nord").hasAttrezzo("osso"));
	}
	
	@Test
	public void testStanzaBuiaLabirinto() {
		Labirinto lab=new Labirinto.LabirintoBuilder()
				.addStanzaIniziale(new StanzaBuia("sala","osso"))
				.getLabirinto();
		Partita p=new Partita(lab,new IOConsole());
		System.out.println(p.getLabirinto().getStanzaVincente());
		assertEquals("Qui c'è buio pesto",p.getLabirinto().getStanzaCorrente().getDescrizione());
	}
}
