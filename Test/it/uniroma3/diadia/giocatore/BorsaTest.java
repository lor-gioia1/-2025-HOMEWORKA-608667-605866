package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	private Borsa borsa;
	private Attrezzo attrezzo;
	
	@BeforeEach
	private void setUp() {
		this.borsa=new Borsa();
		this.attrezzo=new Attrezzo("osso",1);
	}
	
	@Test
	void testAddAttrezzoTrue() {
		assertTrue(borsa.addAttrezzo(attrezzo));
	}
	
	@Test
	void testAddAttrezzoFalse() {
		attrezzo=new Attrezzo("martello",12);
		assertFalse(borsa.addAttrezzo(attrezzo));
	}
	
	@Test
	void testAddAttrezzoFalse1() {
		for(int i=0;i<10;i++) {
			borsa.addAttrezzo(attrezzo);
		}
		assertFalse(borsa.addAttrezzo(attrezzo));
	}
	
	@Test
	void testGetAttrezzoNull() {
		assertNull(borsa.getAttrezzo("martello"));
	}

	@Test
	void testGetAttrezzoEquals() {
		borsa.addAttrezzo(attrezzo);
		borsa.addAttrezzo(new Attrezzo("Spada",7));
		assertEquals("Spada",borsa.getAttrezzo("Spada").getNome());
	}
	
	@Test
	void testGetAttrezzoNotEquals() {
		borsa.addAttrezzo(attrezzo);
		borsa.addAttrezzo(new Attrezzo("Spada",7));
		assertNotEquals("Costantino",borsa.getAttrezzo("Spada").getNome());
	}
	
	@Test
	void testRemoveAttrezzoNull() {
		assertNull(borsa.removeAttrezzo("osso"));
	}
	
	@Test
	void testRemoveAttrezzoEquals() {
		borsa.addAttrezzo(attrezzo);
		assertEquals("osso",borsa.removeAttrezzo("osso").getNome());
	}
	
	@Test
	void testRemoveAttrezzoNotEquals() {
		borsa.addAttrezzo(attrezzo);
		assertNull(borsa.removeAttrezzo("Spada"));
	}
	
	@Test
	void testHasAttrezzoFalse() {
		assertFalse(borsa.hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	void testHasAttrezzoTrue() {
		borsa.addAttrezzo(attrezzo);
		assertTrue(borsa.hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	void testHasAttrezzoFalse1() {
		borsa.addAttrezzo(attrezzo);
		assertFalse(borsa.hasAttrezzo("Spada"));
	}
	
	@Test
	void testIsEmptyTrue() {
		assertTrue(borsa.isEmpty());
	}
	
	@Test
	void testIsEmptyFalse() {
		borsa.addAttrezzo(attrezzo);
		assertFalse(borsa.isEmpty());
	}
	
	@Test
	void testIsEmptyTrue1() {
		borsa.addAttrezzo(attrezzo);
		borsa.removeAttrezzo(attrezzo.getNome());
		assertTrue(borsa.isEmpty());
	}
}
