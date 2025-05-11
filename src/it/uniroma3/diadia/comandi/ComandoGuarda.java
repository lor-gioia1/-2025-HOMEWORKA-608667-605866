package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{

	@Override
	public void esegui(Partita partita) {
		partita.getStampa().mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		partita.getStampa().mostraMessaggio("Cfu rimasti: "+partita.getGiocatore().getCfu());
	}
	
	@Override
	public void setParametro(String parametro) {};
	
	@Override
	public String getNome() {
		return "guarda";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
