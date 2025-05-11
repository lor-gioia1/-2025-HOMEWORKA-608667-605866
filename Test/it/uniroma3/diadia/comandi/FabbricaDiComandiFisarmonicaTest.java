package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandi comandoDaEseguire;
	
	
	@BeforeEach
	public void setUp() throws Exception{
		this.comandoDaEseguire=new FabbricaDiComandiFisarmonica();
		
	}
	
	@Test
	void testCostruisciComandoVai() {
		assertEquals("vai",this.comandoDaEseguire.costruisciComando("vai sud").getNome());
	}
	
	@Test
	void testCostruisciComandoPosa() {
		assertEquals("posa",this.comandoDaEseguire.costruisciComando("posa osso").getNome());
	}
	
	@Test
	void testCostruisciComandoPrendi() {
		assertEquals("prendi",this.comandoDaEseguire.costruisciComando("prendi osso").getNome());
	}
	
	@Test
	void testCostruisciComandoGuarda() {
		assertEquals("guarda",this.comandoDaEseguire.costruisciComando("guarda").getNome());
	}
	
	@Test
	void testCostruisciComandoAiuto() {
		assertEquals("aiuto",this.comandoDaEseguire.costruisciComando("aiuto").getNome());
	}
	
	@Test
	void testCostruisciComandoNonValido() {
		assertEquals("non valido",this.comandoDaEseguire.costruisciComando("vaiii").getNome());
	}
	
	@Test
	void testCostruisciComandoFine() {
		assertEquals("fine",this.comandoDaEseguire.costruisciComando("fine").getNome());
	}
}
