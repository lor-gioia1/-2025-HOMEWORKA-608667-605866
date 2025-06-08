
package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

import it.uniroma3.diadia.ambienti.Labirinto;

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

	private Partita partita;
	private IO io;

	public DiaDia(IO io) {
		this.partita = new Partita(io);
		this.io=io;
	}

	public DiaDia(Labirinto labirinto, IO io) {
		this.partita = new Partita(labirinto, io);
		this.io=io;
	}

	public void gioca() {
		String istruzione; 
		io.mostraMessaggio(Stringhe.Iniziale.getStringa());

		do	{
			istruzione = io.leggiRiga();
		}
		while (!processaIstruzione(istruzione));

	}   

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	public boolean processaIstruzione(String istruzione) {
		if(istruzione.equals("No")&&this.partita.getLabirinto().getStanzaCorrente().getNome().equals(this.partita.getLabirinto().getStanzaVincente().getNome())) {
			io.mostraMessaggio("Heyyyy");
			io.mostraMessaggio(Stringhe.Vittoria.getStringa());
			return true;
		}
		else {
			FabbricaDiComandi comandoDaEseguire=new FabbricaDiComandiRiflessiva();
			comandoDaEseguire.costruisciComando(istruzione).esegui(this.partita);;
			if (this.partita.isFinita()) {
				if (this.partita.vinta()) {
					io.mostraMessaggio(Stringhe.Vittoria.getStringa());
				}
				else {
					io.mostraMessaggio(Stringhe.Sconfitta.getStringa());
				}
				return true;
			}   
			return false;
		}
	}

	public static void main(String[] argc) {
		try {
			IO io=new IOConsole();
			DiaDia gioco = new DiaDia(io);
			gioco.gioca();
			io.getScan().close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}