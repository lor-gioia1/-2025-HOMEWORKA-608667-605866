package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{

	public Cane() {
		super("Cane","Occhio che mordo!");
	}
	public Cane(String nome,String presentazione) {
		super(nome,presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().removeCfu();
		return("Sei stato morso... Ora ti rimangono "+partita.getGiocatore().getCfu()+" cfu");
	}
	
	@Override
	public String riceviRegalo(Attrezzo a,Partita partita) {
		if(a.getNome().equals("cibo")) {
			partita.getGiocatore().getBorsa().removeAttrezzo("cibo");
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(new Attrezzo("bastone",2));
			return("Cibo! Cibo! Cibo!");
		}
		else
			return agisci(partita);
	}
}
