package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.StringReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;

class CaricatoreLabirintoTest {

	private StringReader str;
	private String monolocale;
	private String bilocale;
	private String tanteStanze;

	@BeforeEach
	public void setUp()throws Exception{
		this.monolocale=("Stanze: N10\n"
				+ "Inizio: N10\n"
				+ "Vincente: N10\n"
				+ "Stanze buie: N10 Spada\n"
				+ "Stanze bloccate: \n"
				+ "Stanze magiche: \n"
				+ "Attrezzi: Osso 5 N10\n"
				+ "Uscite: \n"
				+ "Personaggi: \n");
		this.bilocale=("Stanze: N10, Labirinto\n"
				+ "Inizio: N10\n"
				+ "Vincente: Labirinto\n"
				+ "Stanze buie: \n"
				+ "Stanze bloccate: \n"
				+ "Stanze magiche: N10 1\n"
				+ "Attrezzi: Osso 5 N10\n"
				+ "Uscite: N10 nord Labirinto\n"
				+ "Personaggi: Strega N10\n");
		this.tanteStanze=("Stanze: N1, N2, N10, Biblioteca\n"
				+ "Inizio: N2\n"
				+ "Vincente: Biblioteca\n"
				+ "Stanze buie: \n"
				+ "Stanze bloccate: N1 sud Chiave\n"
				+ "Stanze magiche: \n"
				+ "Attrezzi: Osso 5 N10, Spada 2 N2\n"
				+ "Uscite: N10 nord N1, N2 est N10, N2 ovest N1\n"
				+ "Personaggi: Strega N10, Mago N1, Boh N2\n");
	}

	@Test
	public void testMonolocale() throws Exception{
		this.str=new StringReader(this.monolocale);
		CaricatoreLabirinto car=new CaricatoreLabirinto(str);
		Labirinto lab=car.carica();
		assertEquals("N10",lab.getStanzaVincente().getNome());
		assertEquals("Qui c'è buio pesto",lab.getStanzaCorrente().getDescrizione());
		assertTrue(lab.getStanzaVincente().hasAttrezzo("Osso"));
	}

	@Test
	public void testBilocale() throws Exception{
		this.str=new StringReader(bilocale);
		CaricatoreLabirinto car=new CaricatoreLabirinto(str);
		Labirinto lab=car.carica();
		assertEquals("N10",lab.getStanzaCorrente().getNome());
		assertEquals("Labirinto",lab.getStanzaCorrente().getStanzaAdiacente("nord").getNome());
		assertTrue(lab.getStanzaCorrente().hasPersonaggio("Strega"));
		lab.getStanzaCorrente().addAttrezzo(new Attrezzo("Spada",2));
		assertTrue(lab.getStanzaCorrente().hasAttrezzo("adapS"));
		
	}

	@Test
	public void testTanteStanza() throws Exception{
		this.str=new StringReader(tanteStanze);
		CaricatoreLabirinto car=new CaricatoreLabirinto(str);
		Labirinto lab=car.carica();
		assertEquals("N10",lab.getStanzaCorrente().getStanzaAdiacente("est").getNome());
		assertTrue(lab.getStanzaCorrente().hasAttrezzo("Spada"));
		assertFalse(lab.getStanzaCorrente().hasPersonaggio("Boh"));
		assertTrue(lab.getStanzaCorrente().getStanzaAdiacente("est").getStanzaAdiacente("nord").hasPersonaggio("Mago"));
		assertEquals("N1",lab.getStanzaCorrente().getStanzaAdiacente("ovest").getStanzaAdiacente("sud").getNome());
	}
}
