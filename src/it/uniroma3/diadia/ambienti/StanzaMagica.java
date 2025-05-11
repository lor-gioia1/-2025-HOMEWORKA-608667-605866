package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza{

	final static private int SOGLIA_MAGICA_DEFAULT=3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	public StanzaMagica (String nome) {
		this(nome,SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati=0;
		this.sogliaMagica=soglia;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.contatoreAttrezziPosati+1>sogliaMagica) {
			attrezzo=this.modificaAttrezzo(attrezzo);
		}
		if(super.addAttrezzo(attrezzo)) {
			contatoreAttrezziPosati++;
			return true;
		}
		else
			return false;
	}
	
	public Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		nomeInvertito=new StringBuilder(attrezzo.getNome());
		nomeInvertito=nomeInvertito.reverse();
		Attrezzo a1=new Attrezzo(nomeInvertito.toString(), attrezzo.getPeso()*2);
		return a1;
	}
}
