package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.*;

class AbstractComandoTest {

	private Partita partita;
	
	@BeforeEach
	public void setUp() throws Exception{
		this.partita=new Partita(new IOConsole());
	}
	
	@Test
	public void testTeletrasporto() {
		AbstractComando teletrasporto=new AbstractComando() {
			@Override
			public void esegui(Partita partita) {
				partita.getLabirinto().setStanzaCorrente(new Stanza("Parco"));
			}
			
			@Override
			public String getNome() {
				return "teletrasporto";
			}
		};
		teletrasporto.esegui(this.partita);
		assertEquals("Parco",this.partita.getLabirinto().getStanzaCorrente().getNome());
	}
	
	@Test
	public void testRiceviBastone(){
		AbstractComando riceviBastone=new AbstractComando() {
			@Override
			public void esegui(Partita partita) {
				partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("Bastone",2));
			}
			
			@Override
			public String getNome() {
				return "ricevi bastone";
			}
		};
		riceviBastone.esegui(this.partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("Bastone"));
	}

	@Test
	public void testRicevi5Cfu(){
		AbstractComando ricevi5Cfu=new AbstractComando() {
			@Override
			public void esegui(Partita partita) {
				partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()+5);
			}
			
			@Override
			public String getNome() {
				return "ricevi 5 cfu";
			}
		};
		ricevi5Cfu.esegui(this.partita);
		assertEquals(25,this.partita.getGiocatore().getCfu());
	}
}
