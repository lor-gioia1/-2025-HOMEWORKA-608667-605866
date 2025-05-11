
package it.uniroma3.diadia;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private boolean finita;
	private Labirinto labirinto;
	private Giocatore giocatore;
	private IO io;

	public Partita(IO io){
		this.finita = false;
		this.giocatore = new Giocatore();
		this.labirinto = new Labirinto();
		this.io=io;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return labirinto.getStanzaCorrente()==labirinto.getStanzaVincente();
	}

	public IO getStampa() {
		return this.io;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public boolean getFinita() {
		return this.finita;
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
}
