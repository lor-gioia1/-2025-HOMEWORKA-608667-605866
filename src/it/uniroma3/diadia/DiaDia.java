
package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole stampa;



	public DiaDia() {
		this.partita = new Partita();
		this.stampa = new IOConsole();
	}

	public void gioca() {
		String istruzione; 
		stampa.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do	{
			istruzione = stampa.leggiRiga();
		}
		while (!processaIstruzione(istruzione));
	}   

	public IOConsole getStampa() {
		return this.stampa;
	}

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if(comandoDaEseguire.getNome()==null)
			stampa.mostraMessaggio("Comando sconosciuto");
		else if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			stampa.mostraMessaggio("Comando sconosciuto");
		if (this.partita.isFinita()) {
			if (this.partita.vinta()) {
				stampa.mostraMessaggio("Hai vinto!");
			}
			else {
				stampa.mostraMessaggio("Hai perso!");
			}
			return true;
		}   
		return false;
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		String mess="";
		for(int i=0; i< elencoComandi.length; i++) { 
			mess=mess+elencoComandi[i];
			if(i<elencoComandi.length-1)
				mess+=" ";
		}
		stampa.mostraMessaggio(mess);
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			stampa.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			stampa.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			partita.getGiocatore().removeCfu();
		}
		stampa.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		stampa.mostraMessaggio("Cfu rimasti: "+partita.getGiocatore().getCfu());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		stampa.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	private void prendi (String attrezzoDaPrendere) {
		Stanza stanza=partita.getLabirinto().getStanzaCorrente();
		if(stanza.hasAttrezzo(attrezzoDaPrendere)==false)
			stampa.mostraMessaggio("L'attrezzo non è presente nella stanza!");
		else {
			Attrezzo a=stanza.getAttrezzo(attrezzoDaPrendere);
			if((partita.getGiocatore().getBorsa().getNumeroAttrezzi()>10)||(partita.getGiocatore().getBorsa().getPeso()+a.getPeso())>partita.getGiocatore().getBorsa().getPesoMax()){
				stampa.mostraMessaggio("L'attrezzo non entra nella borsa!");
			}
			else {
				stanza.removeAttrezzo(a);
				partita.getGiocatore().getBorsa().addAttrezzo(a);
				stampa.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
			}
		}
	}

	private void posa(String attrezzoDaPosare) {
		Stanza stanza=partita.getLabirinto().getStanzaCorrente();
		if(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzoDaPosare)==false) {
			stampa.mostraMessaggio("L'attrezzo non è presente nella borsa!");
		}
		else {
			if(stanza.getNumeroAttrezzi()>10) {
				stampa.mostraMessaggio("L'attrezzo non è presente nella borsa!");
			}
			else {
				Attrezzo a=partita.getGiocatore().getBorsa().getAttrezzo(attrezzoDaPosare);
				stanza.addAttrezzo(a);
				partita.getGiocatore().getBorsa().removeAttrezzo(a.getNome());
				stampa.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
			}
		}
	}

	public static void main(String[] argc) {

		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}