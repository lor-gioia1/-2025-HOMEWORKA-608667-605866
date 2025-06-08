package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		partita.getStampa().mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		partita.getStampa().mostraMessaggio("Cfu rimasti: "+partita.getGiocatore().getCfu());
		partita.getStampa().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	
	@Override
	public String getNome() {
		return "guarda";
	}
}
