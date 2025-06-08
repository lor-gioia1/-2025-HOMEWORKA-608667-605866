package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	
	public Strega() {
		super("Strega","Posso portarti in una stanza adiacente");
	}
	
	public Strega(String nome, String presentazione) {
		super(nome,presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		int min=10;
		int max=0;
		Stanza smin=null;
		Stanza smax=null;
		for(Direzione d : Direzione.values()) {
			Stanza s=partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(d.toString());
			if(s!=null) {
				if(s.getNumeroAttrezzi()>max)
					smax=s;
				if(s.getNumeroAttrezzi()<min)
					smin=s;
			}
			if(smin==null||smax==null)
				return "Non ci sono stanze adiacenti";
		}
		if(this.haSalutato()) {
			partita.getLabirinto().setStanzaCorrente(smax);
			return ("Per ricambiare la gentilezza, ti manderò nella stanza con più attrezzi!");
		}
		else {
			partita.getLabirinto().setStanzaCorrente(smin);
			return("Sei stato scortese! Ti manderò nella stanza con meno attrezzi!");
		}
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		return("AHAHAHAH! Grazie per il regalo");
	}

}
