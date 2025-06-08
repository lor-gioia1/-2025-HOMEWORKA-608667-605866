package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.*;

public class ComandoSaluta extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio=partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if(personaggio==null) {
			partita.getStampa().mostraMessaggio("Non ci sono personaggi nella stanza");
		}
		else
			partita.getStampa().mostraMessaggio(personaggio.saluta());
	}

	@Override
	public String getNome() {
		return "saluta";
	}
}
