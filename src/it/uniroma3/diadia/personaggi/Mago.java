package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

public class Mago extends AbstractPersonaggio{
	public static final String MESSAGGIO_DONO= "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private Attrezzo attrezzo;
	
	public Mago() {
		super("Mago","Posso donarti un attrezzo!");
		this.attrezzo=new Attrezzo("spada",2);
	}
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome,presentazione);
		this.attrezzo=attrezzo;
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if(this.attrezzo!=null) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
			this.attrezzo=null;
			msg=MESSAGGIO_DONO;
		}
		else {
			msg=MESSAGGIO_SCUSE;
		}
	return msg;
	}
	
	@Override
	public String riceviRegalo(Attrezzo a, Partita partita) {
		partita.getGiocatore().getBorsa().removeAttrezzo(a.getNome());
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(new Attrezzo(a.getNome(),a.getPeso()/2));
		return ("Nella stanza troverai un regalo! Il tuo attrezzo "+a.getNome()+" ora pesa la metà!");
	}
}
