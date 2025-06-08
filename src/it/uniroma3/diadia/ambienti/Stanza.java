
package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.*;
import it.uniroma3.diadia.personaggi.*;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private Set<Attrezzo> attrezzi;
	private Map<Direzione,Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;



	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new EnumMap<>(Direzione.class);
		this.attrezzi = new HashSet<>();
		this.personaggio=null;
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		Direzione dir=Direzione.valueOf(direzione);
		if(this.stanzeAdiacenti.get(dir)==null) {
			this.stanzeAdiacenti.put(dir,stanza);
			stanza.impostaStanzaAdiacente(dir.opposta().toString(),this);
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		if(!direzione.equals("nord")&&!direzione.equals("sud")&&!direzione.equals("est")&&!direzione.equals("ovest")){
			return null;
		}
		Direzione dir=Direzione.valueOf(direzione);
		return this.stanzeAdiacenti.get(dir);
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Set<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi.add(attrezzo);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (Direzione d : Direzione.values()) {
			if(this.stanzeAdiacenti.get(d)!=null)
				risultato.append(" " + d);
		}
		risultato.append("\nAttrezzi nella stanza: ");
		Iterator<Attrezzo>s=attrezzi.iterator();
		while(s.hasNext()) {
			risultato.append(s.next().toString()+" ");
		}
		risultato.append("\nPersonaggi nella stanza: "+this.getPersonaggio());
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		Iterator<Attrezzo> s=attrezzi.iterator();
		while(s.hasNext()) {
			if (s.next().getNome().equals(nomeAttrezzo))
				trovato = true;
		}
		return trovato;
	}

	public boolean hasPersonaggio(String nome) {
		if(this.personaggio==null)
			return false;
		return this.personaggio.getNome().equals(nome);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		Iterator<Attrezzo>s=attrezzi.iterator();
		while(s.hasNext()) {
			Attrezzo temp=s.next();
			if (temp.getNome().equals(nomeAttrezzo))
				attrezzoCercato = temp;
		}
		return attrezzoCercato;	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(attrezzo==null)
			return false;
		if(this.attrezzi.contains(attrezzo)) {
			this.attrezzi.remove(attrezzo);
			return true;
		}
		return false;
	}

	public void addPersonaggio(AbstractPersonaggio personaggio) {
		if(this.personaggio==null)
			this.personaggio=personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

	@Override
	public boolean equals(Object o) {
		Stanza that=(Stanza)o;
		return this.getNome().equals(that.getNome());
	}

	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}
}