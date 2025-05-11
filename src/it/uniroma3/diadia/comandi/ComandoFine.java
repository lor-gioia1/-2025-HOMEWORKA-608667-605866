package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	
	@Override
	public void esegui(Partita partita) {
		partita.getStampa().mostraMessaggio("Grazie di aver giocato!");
	}
	
	@Override
	public void setParametro(String parametro) {};
	
	@Override
	public String getNome() {
		return "fine";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
