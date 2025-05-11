package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoVaiTest {

	private Partita partita;
	private FabbricaDiComandi comandoDaEseguire;
	
	@BeforeEach
	public void setUp() throws Exception{
		IOConsole io=new IOConsole();
		this.partita=new Partita(io);
		this.comandoDaEseguire=new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	void testEseguiDirezioneInesistente() {
		comandoDaEseguire.costruisciComando("vai su").esegui(this.partita);
		assertEquals("Atrio",this.partita.getLabirinto().getStanzaCorrente().getNome());
	}
	
	@Test
	void testEseguiSud() {
		this.comandoDaEseguire.costruisciComando("vai sud").esegui(this.partita);
		assertEquals("Aula N10",this.partita.getLabirinto().getStanzaCorrente().getNome());
	}
	
	@Test
	void testEseguiVinta() {
		this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(new Attrezzo("lanterna",1));
		this.comandoDaEseguire.costruisciComando("vai nord").esegui(this.partita);
		assertEquals(this.partita.getLabirinto().getStanzaVincente(),this.partita.getLabirinto().getStanzaCorrente());
	}
	
	@Test
	void testEseguiParametro() {
		Comando v=this.comandoDaEseguire.costruisciComando("vai");
		v.setParametro("est");
		v.esegui(this.partita);
		assertEquals("Aula N11",this.partita.getLabirinto().getStanzaCorrente().getNome());
	}
}
