package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.*;

class IOSimulatorTest {

	private IOSimulator io;
	private DiaDia gioco;
	
	@Test
	public void testPartitaFinitaComandiNonValidi(){
		String[] cmd= {"vai nord","vai","vaiii","vai su","fine"};
		this.io=new IOSimulator(cmd);
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
	public void testPartitaVintaStanzaBloccata() {
		String[] cmd= {"vai sud","prendi lanterna","vai nord","posa lanterna","vai nord"};
		this.io=new IOSimulator(cmd);
		this.gioco=new DiaDia(io);
		for (int i=0;i<=5;i++)
			gioco.processaIstruzione(this.io.leggiRiga());
		assertEquals("Aula N10",this.io.getMessaggio(0));
		assertEquals("Contenuto borsa (3kg/10kg): lanterna 3 kg ",this.io.getMessaggio(1));
		assertEquals("Atrio",this.io.getMessaggio(2));
		assertEquals("Borsa vuota",this.io.getMessaggio(3));
		assertEquals("Biblioteca",this.io.getMessaggio(4));
		assertEquals("Hai vinto",this.io.getMessaggio(5));
	}

	@Test
	public void testPartitaFinitaStanzaBuia() {
		String[] cmd= {"prendi osso","vai ovest","guarda","posa osso","guarda","fine"};
		this.io=new IOSimulator(cmd);
		this.gioco=new DiaDia(io);
		for (int i=0;i<=6;i++)
			gioco.processaIstruzione(this.io.leggiRiga());
		assertEquals("Contenuto borsa (1kg/10kg): osso 1 kg ",this.io.getMessaggio(0));		
		assertEquals("Laboratorio Campus",this.io.getMessaggio(1));
		assertEquals("Qui c'è buio pesto",this.io.getMessaggio(2));
		assertEquals("Cfu rimasti: 19",this.io.getMessaggio(3));
		assertEquals("Contenuto borsa (1kg/10kg): osso 1 kg ",this.io.getMessaggio(4));
		assertEquals("Borsa vuota",this.io.getMessaggio(5));	
		assertEquals("Laboratorio Campus\n"
				+ "Uscite:  est ovest\n"
				+ "Attrezzi nella stanza: osso 1 kg cibo 3 kg \n"
				+ "Personaggi nella stanza: Cane",this.io.getMessaggio(6));
		assertEquals("Cfu rimasti: 19",this.io.getMessaggio(7));
		assertEquals("Borsa vuota",this.io.getMessaggio(8));
		assertEquals("Grazie di aver giocato!",this.io.getMessaggio(9));
	}
	
	@Test
	public void testPartitaFinitaStanzaMagica() {
		String[] cmd= {"prendi osso","vai est","posa osso","prendi osso","posa osso","prendi osso","posa osso","prendi osso","posa osso","guarda","fine"};
		this.io=new IOSimulator(cmd);
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
				+ "Attrezzi nella stanza: osso 2 kg \n"
				+ "Personaggi nella stanza: Mago",this.io.getMessaggio(9));
		assertEquals("Cfu rimasti: 19",this.io.getMessaggio(10));
		assertEquals("Borsa vuota",this.io.getMessaggio(11));
		assertEquals("Grazie di aver giocato!",this.io.getMessaggio(12));
	}
	
	@Test
	public void testPartitaLabrinitoMonolocale() {
		Labirinto lab=new Labirinto.LabirintoBuilder().addStanza("salotto").getLabirinto();
		String[] cmd= {"guarda"};
		this.io=new IOSimulator(cmd);
		DiaDia gioco=new DiaDia(lab,io);
		gioco.processaIstruzione(this.io.leggiRiga());
		assertEquals("salotto\n"
				+ "Uscite: \n"
				+ "Attrezzi nella stanza: \n"
				+ "Personaggi nella stanza: null",this.io.getMessaggio(0));
	}
	
	@Test
	public void testPartitaLabrinitoBilocale() {
		Labirinto lab=new Labirinto.LabirintoBuilder().addStanzaVincente("salotto")
				.addStanzaIniziale(new StanzaBuia("camera","osso"))
				.addAdiacenza("camera", "salotto", "nord")
				.getLabirinto();
		String[] cmd= {"guarda","vai nord"};
		this.io=new IOSimulator(cmd);
		DiaDia gioco=new DiaDia(lab,io);
		gioco.processaIstruzione(this.io.leggiRiga());
		gioco.processaIstruzione(this.io.leggiRiga());
		assertEquals("Qui c'è buio pesto",this.io.getMessaggio(0));
		assertEquals("Cfu rimasti: 20",this.io.getMessaggio(1));
		assertEquals("Borsa vuota",this.io.getMessaggio(2));
		assertEquals("salotto",this.io.getMessaggio(3));
		assertEquals("Hai vinto",this.io.getMessaggio(4));
	}
}
