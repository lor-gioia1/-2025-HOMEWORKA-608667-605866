package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

public class ComandoPosa extends AbstractComando {

	private String attrezzoDaPosare;
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanza=partita.getLabirinto().getStanzaCorrente();
		if(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzoDaPosare)==false) {
			partita.getStampa().mostraMessaggio("L'attrezzo non è presente nella borsa!");
		}
		else {
			if(stanza.getNumeroAttrezzi()>10||stanza.hasAttrezzo(attrezzoDaPosare)) {
				partita.getStampa().mostraMessaggio("L'attrezzo non entra nella stanza!");
			}
			else {
				Attrezzo a=partita.getGiocatore().getBorsa().getAttrezzo(attrezzoDaPosare);
				stanza.addAttrezzo(a);
				partita.getGiocatore().getBorsa().removeAttrezzo(a.getNome());
				partita.getStampa().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
			}
		}
	}
	
	@Override
	public void setParametro (String parametro) {
		this.attrezzoDaPosare=parametro;
	}
	
	@Override
	public String getNome() {
		return "posa";
	}
}
