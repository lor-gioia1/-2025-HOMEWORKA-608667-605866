package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda", "saluta", "interagisci", "regala"};
	
	@Override
	public void esegui (Partita partita) {
		String mess="";
		for(int i=0; i< elencoComandi.length; i++) { 
			mess=mess+elencoComandi[i];
			if(i<elencoComandi.length-1)
				mess+=" ";
		}
		partita.getStampa().mostraMessaggio(mess);
	}
	
	@Override
	public String getNome() {
		return "aiuto";
	}
}
