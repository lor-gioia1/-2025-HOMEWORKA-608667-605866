package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	private StanzaBuia s1;
	private Attrezzo a1;
	
	@BeforeEach
	public void setUp() throws Exception{
		this.s1=new StanzaBuia("Aula N1","chiave");
		this.a1=new Attrezzo("chiave",1);
	}
	
	@Test
	void testGetDescrizioneBuio() {
		assertEquals("Qui c'è buio pesto",s1.getDescrizione());
	}

	@Test
	void testGetDescrizioneStanza() {
		s1.addAttrezzo(a1);
		assertEquals(s1.toString(),s1.getDescrizione());
	}
	
	@Test
	void testGetDescrizioneOsso() {
		s1.addAttrezzo(new Attrezzo("osso",2));
		assertEquals("Qui c'è buio pesto",s1.getDescrizione());
	}
}