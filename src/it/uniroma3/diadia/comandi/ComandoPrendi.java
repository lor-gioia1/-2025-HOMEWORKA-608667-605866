package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando{
	
	private String attrezzoDaPrendere;
	
	@Override
	public void esegui (Partita partita) {
		Stanza stanza=partita.getLabirinto().getStanzaCorrente();
		if(stanza.hasAttrezzo(attrezzoDaPrendere)==false)
			partita.getStampa().mostraMessaggio("L'attrezzo non è presente nella stanza!");
		else {
			Attrezzo a=stanza.getAttrezzo(attrezzoDaPrendere);
			if((partita.getGiocatore().getBorsa().getNumeroAttrezzi()>10)||(partita.getGiocatore().getBorsa().getPeso()+a.getPeso())>partita.getGiocatore().getBorsa().getPesoMax()){
				partita.getStampa().mostraMessaggio("L'attrezzo non entra nella borsa!");
			}
			else {
				stanza.removeAttrezzo(a);
				partita.getGiocatore().getBorsa().addAttrezzo(a);
				partita.getStampa().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
			}
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.attrezzoDaPrendere=parametro;
	}
	
	@Override
	public String getNome() {
		return "prendi";
	}
}
