package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private StanzaBloccata s1;
	private Stanza s2;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.s1=new StanzaBloccata("Aula N1","nord","chiave");
		this.s2=new Stanza("Aula N2");
		s1.impostaStanzaAdiacente("nord", s2);
	}
	
	@Test
	void testGetStanzaAdiacenteTrue() {
		assertEquals(s1,s1.getStanzaAdiacente("nord"));
	}

	@Test
	void testGetStanzaAdiacenteChiave() {
		s1.addAttrezzo(new Attrezzo("chiave",1));
		assertEquals(s2,s1.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testGetStanzaAdiacenteOsso() {
		s1.addAttrezzo(new Attrezzo("osso",1));
		assertEquals(s1,s1.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testGetDescrizioneDirezioneBloccata() {
		assertEquals(s1.getDescrizione(), s1.toString()+"\n"+"la direzione bloccata è nord");
	}
	
	@Test
	void testGetDescrizioneS2() {
		s1.addAttrezzo(new Attrezzo("chiave",1));
		assertEquals(s1.getStanzaAdiacente("nord").getDescrizione(), s2.toString());
	}
	
	@Test
	void testGetDescrizioneS1() {
		assertEquals(s1.getStanzaAdiacente("nord").getDescrizione(), s1.toString()+"\n"+"la direzione bloccata è nord");
	}
}