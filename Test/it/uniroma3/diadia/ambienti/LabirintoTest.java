package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {

	private Labirinto labirinto;
	
	@BeforeEach
	private void setUp() {
		this.labirinto=new Labirinto();
	}
	
	@Test
	void testGetStanzaCorrenteEquals() {
		assertEquals("Atrio",labirinto.getStanzaCorrente().getNome());
	}
	
	@Test
	void testGetStanzaCorrenteNotEquals() {
		assertNotEquals("Aula N10",labirinto.getStanzaCorrente().getNome());
	}
	
	@Test
	void testGetStanzaCorrenteNot() {
		labirinto.setStanzaCorrente(new Stanza("Aula N9"));
		assertEquals("Aula N9",labirinto.getStanzaCorrente().getNome());
	}
	
	@Test
	void testGetStanzaVincenteEquals() {
		assertEquals("Biblioteca",labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	void testGetStanzaVincenteNotEquals() {
		assertNotEquals("Atrio",labirinto.getStanzaVincente().getNome());
	}

	@Test
	void testGetStanzaVincente() {
		labirinto.setStanzaCorrente(labirinto.getStanzaVincente());
		assertEquals(labirinto.getStanzaVincente().getNome(),labirinto.getStanzaCorrente().getNome());
	}
}
