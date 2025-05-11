package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{

	private String ricercato;
	
	public StanzaBuia(String nome, String ricercato) {
		super(nome);
		this.ricercato=ricercato;
	}
	
	@Override
	public String getDescrizione() {
		if (this.hasAttrezzo(this.ricercato)) {
			return super.getDescrizione();
		}
		return ("Qui c'è buio pesto");
	}
}