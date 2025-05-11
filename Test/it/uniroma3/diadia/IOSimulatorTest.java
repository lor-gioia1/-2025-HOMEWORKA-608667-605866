package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class IOSimulatorTest {

	private IOSimulator io;
	private DiaDia gioco;
	
	@Test
	void testPartitaFinitaComandiNonValidi(){
		String[] comandi= {"vai nord","vai","vaiii","vai su","fine"};
		this.io=new IOSimulator(comandi);
		this.gioco=new DiaDia(io);
		for (int i=0;i<=5;i++)
			gioco.processaIstruzione(this.io.leggiRiga());
		assertEquals("Atrio",this.io.getMessaggio(0));
		assertEquals("Dove vuoi andare?",this.io.getMessaggio(1));
		assertEquals("Comando non valido",this.io.getMessaggio(2));
		assertEquals("Direzione inesistente",this.io.getMessaggio(3));
		assertEquals("Grazie di aver giocato!",this.io.getMessaggio(4));
	}
	
	@Test
	void testPartitaVintaStanzaBloccata() {
		String[] comandi= {"vai sud","prendi lanterna","vai nord","posa lanterna","vai nord"};
		this.io=new IOSimulator(comandi);
		this.gioco=new DiaDia(io);
		for (int i=0;i<=5;i++)
			gioco.processaIstruzione(this.io.leggiRiga());
		assertEquals("Aula N10",this.io.getMessaggio(0));
		assertEquals("Contenuto borsa (3kg/10kg): lanterna 3 kg ",this.io.getMessaggio(1));
		assertEquals("Atrio",this.io.getMessaggio(2));
		assertEquals("Borsa vuota",this.io.getMessaggio(3));
		assertEquals("Biblioteca",this.io.getMessaggio(4));
		assertEquals("Hai vinto!",this.io.getMessaggio(5));
	}

	@Test
	void testPartitaFinitaStanzaBuia() {
		String[] comandi= {"prendi osso","vai ovest","guarda","posa osso","guarda","fine"};
		this.io=new IOSimulator(comandi);
		this.gioco=new DiaDia(io);
		for (int i=0;i<=6;i++)
			gioco.processaIstruzione(this.io.leggiRiga());
		assertEquals("Contenuto borsa (1kg/10kg): osso 1 kg ",this.io.getMessaggio(0));		
		assertEquals("Laboratorio Campus",this.io.getMessaggio(1));
		assertEquals("Qui c'è buio pesto",this.io.getMessaggio(2));
		assertEquals("Cfu rimasti: 19",this.io.getMessaggio(3));	
		assertEquals("Borsa vuota",this.io.getMessaggio(4));	
		assertEquals("Laboratorio Campus\n"
				+ "Uscite:  est ovest\n"
				+ "Attrezzi nella stanza: osso 1 kg ",this.io.getMessaggio(5));
		assertEquals("Cfu rimasti: 19",this.io.getMessaggio(6));
		assertEquals("Grazie di aver giocato!",this.io.getMessaggio(7));
	}
	
	@Test
	void testPartitaFinitaStanzaMagica() {
		String[] comandi= {"prendi osso","vai est","posa osso","prendi osso","posa osso","prendi osso","posa osso","prendi osso","posa osso","guarda","fine"};
		this.io=new IOSimulator(comandi);
		this.gioco=new DiaDia(io);
		for (int i=0;i<=11;i++)
			gioco.processaIstruzione(this.io.leggiRiga());
		assertEquals("Contenuto borsa (1kg/10kg): osso 1 kg ",this.io.getMessaggio(0));
		assertEquals("Aula N11",this.io.getMessaggio(1));
		assertEquals("Borsa vuota",this.io.getMessaggio(2));
		assertEquals("Contenuto borsa (1kg/10kg): osso 1 kg ",this.io.getMessaggio(3));
		assertEquals("Borsa vuota",this.io.getMessaggio(8));
		assertEquals("Aula N11\n"
				+ "Uscite:  est ovest\n"
				+ "Attrezzi nella stanza: osso 2 kg ",this.io.getMessaggio(9));
		assertEquals("Cfu rimasti: 19",this.io.getMessaggio(10));
		assertEquals("Grazie di aver giocato!",this.io.getMessaggio(11));
	}
}
