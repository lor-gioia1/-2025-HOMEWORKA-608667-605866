package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};
	
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
	public void setParametro(String parametro) {};
	
	@Override
	public String getNome() {
		return "aiuto";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
