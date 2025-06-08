package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;

public class ComandoNonValido extends AbstractComando{
	
	@Override
	public void esegui(Partita partita) {
		partita.getStampa().mostraMessaggio(Stringhe.ComandoNonValido.getStringa());
	}
	
	@Override
	public String getNome() {
		return "non valido";
	}
}
