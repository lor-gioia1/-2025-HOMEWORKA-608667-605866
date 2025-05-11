package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.jupiter.api.Test;

class StanzaMagicaTest {


	private StanzaMagica s1;
	private Attrezzo a1;
	private Attrezzo a2;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		this.s1=new StanzaMagica("Aula N1",2);
		this.a1=new Attrezzo("osso",1);
		this.a2=new Attrezzo("martello",1);
	}
	
	@Test
	void testAddAttrezzoTrue() {
		s1.addAttrezzo(this.a1);
		assertTrue(s1.hasAttrezzo("osso"));
	}
	
	@Test
	void testAddAttrezzoFalse() {
		for (int i=0;i<10;i++)
			s1.addAttrezzo(this.a1);
		s1.addAttrezzo(this.a2);
		assertFalse(s1.hasAttrezzo("martello"));
	}
	
	@Test
	void testModificaAttrezzoSpada() {
		s1.addAttrezzo(this.a2);
		this.a2=s1.modificaAttrezzo(this.a2);
		assertEquals("olletram",this.a2.getNome());
		assertEquals(2,this.a2.getPeso());
	}
	
	@Test
	void testModificaAttrezzoTrue() {
		for (int i=0;i<2;i++)
			s1.addAttrezzo(this.a1);
		s1.addAttrezzo(this.a2);
		assertTrue(s1.hasAttrezzo("olletram"));
		assertFalse(s1.hasAttrezzo("martello"));
	}

	@Test
	void testModificaAttrezzoFalse() {
		s1.addAttrezzo(this.a1);
		s1.addAttrezzo(this.a2);
		assertFalse(s1.hasAttrezzo("olletram"));
		assertTrue(s1.hasAttrezzo("martello"));
	}	
}
