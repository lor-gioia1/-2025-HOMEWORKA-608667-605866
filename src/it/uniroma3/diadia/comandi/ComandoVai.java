package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.Partita;

public class ComandoVai implements Comando{
	
	private String direzione;
	
	@Override
	public void esegui (Partita partita) {
		if(direzione==null) {
			partita.getStampa().mostraMessaggio("Dove vuoi andare?");
			return;
		}
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			partita.getStampa().mostraMessaggio("Direzione inesistente");
			return;
		}
		else {
			partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			partita.getGiocatore().removeCfu();
		}
		partita.getStampa().mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getNome());
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione=parametro;
	}
	
	@Override
	public String getNome() {
		return "vai";
	}
	
	@Override
	public String getParametro() {
		return direzione;
	}
}
