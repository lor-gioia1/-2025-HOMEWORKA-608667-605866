package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	
	private String direzioneBloccata;
	private String attrezzoSblocca;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSblocca) {
		super(nome);
		this.direzioneBloccata=direzioneBloccata;
		this.attrezzoSblocca=attrezzoSblocca;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if(!this.hasAttrezzo(attrezzoSblocca)&&dir.equals(direzioneBloccata)) {
			return this;
		}
		return super.getStanzaAdiacente(dir);
	}

	@Override
	public String getDescrizione() {
		return (super.getDescrizione()+"\n"+"la direzione bloccata è "+direzioneBloccata);
	}
}