package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.*;

class BorsaTest {

	private Borsa borsa;
	private Attrezzo attrezzo;
	private Attrezzo piombo;
	private Attrezzo piuma;
	private Attrezzo ps;
	private Attrezzo libro;
	
	@BeforeEach
	private void setUp() {
		this.borsa=new Borsa();
		this.attrezzo=new Attrezzo("osso",1);
		this.piombo=new Attrezzo("piombo",10);
		this.piuma=new Attrezzo("piuma",1);
		this.ps=new Attrezzo("ps",5);
		this.libro=new Attrezzo("libro",5);
		
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
	
	@Test
	void testGetContenutoOrdinatoPerPeso() {
		Borsa borsa1=new Borsa(30);
		borsa1.addAttrezzo(piombo);
		borsa1.addAttrezzo(ps);
		borsa1.addAttrezzo(piuma);
		borsa1.addAttrezzo(libro);
		List <Attrezzo> a=borsa1.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo>it=a.iterator();
		assertEquals("piuma",it.next().getNome());
		assertEquals("libro",it.next().getNome());
		assertEquals("ps",it.next().getNome());
		assertEquals("piombo",it.next().getNome());
	}
	
	@Test
	void testGetContenutoOrdinatoPerNome() {
		Borsa borsa1=new Borsa(30);
		borsa1.addAttrezzo(piombo);
		borsa1.addAttrezzo(ps);
		borsa1.addAttrezzo(piuma);
		borsa1.addAttrezzo(libro);
		Set <Attrezzo> a=borsa1.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo>it=a.iterator();
		assertEquals("libro",it.next().getNome());
		assertEquals("piombo",it.next().getNome());
		assertEquals("piuma",it.next().getNome());
		assertEquals("ps",it.next().getNome());
	}
	
	@Test
	void testGetContenutoRaggruppatoPerPeso() {
		Borsa borsa1=new Borsa(30);
		borsa1.addAttrezzo(piombo);
		borsa1.addAttrezzo(ps);
		borsa1.addAttrezzo(piuma);
		borsa1.addAttrezzo(libro);
		Map <Integer, Set<Attrezzo>> a=borsa1.getContenutoRaggruppatoPerPeso();
		assertEquals(1,a.get(piuma.getPeso()).size());
		assertEquals(1,a.get(piombo.getPeso()).size());
		assertEquals(2,a.get(ps.getPeso()).size());
		Iterator<Attrezzo>it=a.get(ps.getPeso()).iterator();
		assertEquals("libro",it.next().getNome());
		assertEquals("ps",it.next().getNome());
	}
	
	@Test
	void testGetSortedSetOrdinatoPerPeso() {
		Borsa borsa1=new Borsa(20);
		borsa1.addAttrezzo(ps);
		borsa1.addAttrezzo(libro);
		Set <Attrezzo> a=borsa1.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo>it=a.iterator();
		assertEquals("libro",it.next().getNome());
		assertEquals("ps",it.next().getNome());
	}
}
