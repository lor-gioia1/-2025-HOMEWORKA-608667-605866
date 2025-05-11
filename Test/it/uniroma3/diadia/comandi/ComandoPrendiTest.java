package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {

	private Partita partita;
	private FabbricaDiComandi comandoDaEseguire;
	
	@BeforeEach
	public void setUp() throws Exception{
		IOConsole io=new IOConsole();
		this.partita=new Partita(io);
		this.comandoDaEseguire=new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	void testEseguiAttrezzoNonTrovato() {
		this.comandoDaEseguire.costruisciComando("prendi spada").esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
	
	@Test
	void testEseguiOsso() {
		this.comandoDaEseguire.costruisciComando("prendi osso").esegui(this.partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
	}
	
	@Test
	void testEseguiSpada() {
		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(new Attrezzo("spada",11));
		this.comandoDaEseguire.costruisciComando("prendi spada").esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
	
	@Test
	void testEseguiParametro() {
		Comando v=this.comandoDaEseguire.costruisciComando("prendi");
		v.setParametro("spada");
		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(new Attrezzo("spada",1));
		v.esegui(this.partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}

}
