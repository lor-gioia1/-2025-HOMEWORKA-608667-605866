package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected{

	final static private int SOGLIA_MAGICA_DEFAULT=3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	public StanzaMagicaProtected (String nome) {
		this(nome,SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati=0;
		this.sogliaMagica=soglia;
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.contatoreAttrezziPosati+1>sogliaMagica) {
			attrezzo=this.modificaAttrezzo(attrezzo);
		}
		if (this.numeroAttrezzi < this.attrezzi.size()) {
			this.attrezzi.add(attrezzo);
			return true;
		}
		else {
			return false;
		}
	}
	
	public Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		nomeInvertito=new StringBuilder(attrezzo.getNome());
		nomeInvertito=nomeInvertito.reverse();
		Attrezzo a1=new Attrezzo(nomeInvertito.toString(), attrezzo.getPeso()*2);
		return a1;
	}
}
