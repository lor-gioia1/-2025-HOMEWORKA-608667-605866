package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {

	private Partita partita;
	private FabbricaDiComandi comandoDaEseguire;
	private Attrezzo spada;
	
	@BeforeEach
	public void setUp() throws Exception{
		IOConsole io=new IOConsole();
		this.partita=new Partita(io);
		this.comandoDaEseguire=new FabbricaDiComandiRiflessiva();
		this.spada=new Attrezzo ("spada",1);
	}
	
	@Test
	void testEseguiFalse() {
		this.comandoDaEseguire.costruisciComando("posa spada").esegui(this.partita);
		assertFalse(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	@Test
	void testEseguiTrue() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("spada",1));
		this.comandoDaEseguire.costruisciComando("posa spada").esegui(this.partita);
		assertTrue(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	@Test
	void testEseguiNonEntra() {
		for(int i=0;i<=9;i++)
			this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(new Attrezzo(String.valueOf(i),1));
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("martello",1));
		this.comandoDaEseguire.costruisciComando("posa martello").esegui(this.partita);
		assertFalse(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("martello"));
	}
	
	@Test
	void testEseguiParametro() {
		AbstractComando v=this.comandoDaEseguire.costruisciComando("posa");
		v.setParametro("spada");
		this.partita.getGiocatore().getBorsa().addAttrezzo(spada);
		v.esegui(this.partita);
		assertTrue(this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo("spada"));
	}
}