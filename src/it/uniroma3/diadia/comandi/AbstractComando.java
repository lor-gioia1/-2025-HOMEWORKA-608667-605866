package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {
	
	abstract public void esegui(Partita partita);
	
	public void setParametro(String parametro) {};
	
	abstract public String getNome();
}
