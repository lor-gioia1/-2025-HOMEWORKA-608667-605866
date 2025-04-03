package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Partita partita;
	private Stanza stanza;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() throws Exception{
		this.partita=new Partita();
		this.stanza=partita.getLabirinto().getStanzaCorrente();
		this.attrezzo=new Attrezzo("spada",2);
	}
	
	@Test
	void testImpostaStanzaAdiacenteN1() {
		stanza.impostaStanzaAdiacente("sud",new Stanza("Aula N1"));
		assertEquals("Aula N1",stanza.getStanzaAdiacente("sud").getNome());
	}
	
	@Test
	void testImpostaStanzaAdiacenteNotEquals() {
		stanza.impostaStanzaAdiacente("est",new Stanza("Aula N1"));
		assertNotEquals("Aula N10",stanza.getStanzaAdiacente("est").getNome());
	}
	
	@Test
	void testImpostaStanzaAdiacenteN11() {
		stanza=new Stanza("Aula N9");
		stanza.impostaStanzaAdiacente("est",new Stanza("Aula N8"));
		assertEquals("Aula N8",stanza.getStanzaAdiacente("est").getNome());
	}
	
	@Test
	void testGetStanzaAdiacenteN11() {
		assertEquals("Aula N11",stanza.getStanzaAdiacente("est").getNome());
	}
	
	@Test
	void testGetStanzaAdiacenteFalse() {
		assertNotEquals("Biblioteca",stanza.getStanzaAdiacente("sud").getNome());
	}
	
	@Test
	void testGetStanzaAdiacenteLaboratorio() {
		assertEquals("Laboratorio Campus",stanza.getStanzaAdiacente("est").getStanzaAdiacente("est").getNome());
	}
	
	@Test
	void testGetStanzaAdiacente() {
		stanza=partita.getLabirinto().getStanzaVincente();
		assertEquals("Atrio",stanza.getStanzaAdiacente("sud").getNome());
	}

	@Test
	void testAddAttrezzoTrue() {
		assertTrue(stanza.addAttrezzo(attrezzo));
	}
	
	@Test
	void testAddAttrezzoFalse() {
		for(int i=1;i<10;i++) {
			stanza.addAttrezzo(attrezzo);
		}
		assertFalse(stanza.addAttrezzo(attrezzo));
	}
	
	@Test
	void testAddAttrezzoNull() {
		assertTrue(stanza.addAttrezzo(null));
	}
	
	@Test
	void testHasAttrezzoTrue() {
		assertTrue(stanza.hasAttrezzo("osso"));
	}
	
	@Test
	void testHasAttrezzoFalse() {
		assertFalse(stanza.hasAttrezzo("martello"));
	}
	
	@Test
	void testHasAttrezzoTrue1() {
		stanza.addAttrezzo(attrezzo);
		assertTrue(stanza.hasAttrezzo("spada"));
	}
	
	@Test
	void testGetAttrezzoTrue() {
		assertEquals("osso",stanza.getAttrezzo("osso").getNome());
	}
	
	@Test
	void testGetAttrezzoFalse() {
		assertNotEquals(attrezzo,stanza.getAttrezzo("martello"));
	}
	
	@Test
	void testGetAttrezzoTrue1() {
		stanza.addAttrezzo(attrezzo);
		assertEquals(attrezzo,stanza.getAttrezzo("spada"));
	}
	
	@Test
	void testRemoveAttrezzoFalse() {
		assertFalse(stanza.removeAttrezzo(attrezzo));
	}
	
	@Test
	void testRemoveAttrezzoTrue() {
		assertTrue(stanza.removeAttrezzo(stanza.getAttrezzo("osso")));
	}
	
	@Test
	void testRemoveAttrezzoTrueAdd() {
		stanza.addAttrezzo(attrezzo);
		assertTrue(stanza.removeAttrezzo(attrezzo));
	}
	
	@Test
	void testRemoveAttrezzoTrue1() {
		stanza.addAttrezzo(attrezzo);
		assertTrue(stanza.hasAttrezzo("spada"));
		assertTrue(stanza.removeAttrezzo(attrezzo));
	}
}
