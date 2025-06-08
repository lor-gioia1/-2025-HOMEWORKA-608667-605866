package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando{

	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio=partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if(personaggio==null)
			partita.getStampa().mostraMessaggio("Non ci sono personaggi nella stanza");
		else {
			Attrezzo a=partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if(a==null)
				partita.getStampa().mostraMessaggio("L'attrezzo non è presente in borsa");
			else {
				partita.getStampa().mostraMessaggio(personaggio.riceviRegalo(a, partita));
			}

		}
	}

	@Override
	public void setParametro(String nome) {
		this.nomeAttrezzo=nome;
	}
	
	@Override
	public String getNome() {
		return ("regala");
	}
}
