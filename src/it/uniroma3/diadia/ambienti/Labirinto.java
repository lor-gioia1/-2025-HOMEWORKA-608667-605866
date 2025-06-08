package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;
import java.util.*;

public class Labirinto {

	static private Stanza stanzaCorrente;
	static private Stanza stanzaVincente;
	static private Stanza stanzaIniziale;
	static private Set<Stanza> stanze;
	static private Stanza ultima;

	public Labirinto(){
		stanze=new HashSet<Stanza>();
		init();
	}
	
	private Labirinto(LabirintoBuilder lab) {
		stanze=lab.getStanze();
		stanzaCorrente=lab.getStanzaInizialeLab();
		stanzaVincente=lab.getStanzaVincenteLab();
	}
	
	public static class LabirintoBuilder {
		
		public LabirintoBuilder() {
			stanze=new HashSet<>();
		}
			
			public LabirintoBuilder addStanzaVincente(String nome) {
				stanzaVincente=new Stanza(nome);
				ultima=stanzaVincente;
				stanze.add(stanzaVincente);
				return this;
			}
			
			public LabirintoBuilder addStanzaVincente(Stanza s) {
				stanzaVincente=s;
				ultima=stanzaVincente;
				stanze.add(stanzaVincente);
				return this;
			}
			
			public Set<Stanza> getStanze(){
				return stanze;
			}
			
			public LabirintoBuilder addStanzaIniziale(String nome) {
				stanzaIniziale=new Stanza(nome);
				ultima=stanzaIniziale;
				stanze.add(stanzaIniziale);
				return this;
			}
			
			public LabirintoBuilder addStanzaIniziale(Stanza s) {
				stanzaIniziale=s;
				ultima=stanzaIniziale;
				stanze.add(stanzaIniziale);
				return this;
			}
			
			public LabirintoBuilder addStanza(String nome) {
				Stanza s=new Stanza(nome);
				stanze.add(s);
				ultima=s;
				return this;
			}
			
			public LabirintoBuilder setStanze(List<Stanza> stan) {
				for(Stanza s : stan) {
					addStanza(s);
				}
				return this;
			}
			
			public LabirintoBuilder addStanza(Stanza s) {
				stanze.add(s);
				ultima=s;
				return this;
			}
			
			public Stanza getStanzaInizialeLab() {
				return stanzaIniziale;
			}
			
			public Stanza getStanzaVincenteLab() {
				return stanzaVincente;
			}
			
			public LabirintoBuilder addAttrezzo(String nome,int peso) {
				ultima.addAttrezzo(new Attrezzo(nome,peso));
				return this;
			}
			
			public Stanza hasStanza(String nome) {
				for(Stanza s : stanze) {
					if(s.getNome().equals(nome))
						return s;
				}
				return null;
			}
			
			public LabirintoBuilder addAdiacenza(String nome1, String nome2, String direzione) {
				Stanza s1=this.hasStanza(nome1);
				Stanza s2=this.hasStanza(nome2);
				if(s1!=null&&s2!=null) {
					s1.impostaStanzaAdiacente(direzione,s2);
				}
				return this;
			}
			
			public Labirinto getLabirinto() {
				if(stanzaIniziale==null&&!stanze.isEmpty()) {
					Iterator<Stanza>it=stanze.iterator();
					stanzaIniziale=it.next();
				}
				if(stanzaVincente==null&&!stanze.isEmpty()) {
					Iterator<Stanza>it=stanze.iterator();
					stanzaVincente=it.next();
				}
				return new Labirinto(this);
			}
		}
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}

	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	private void init() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo cibo = new Attrezzo("cibo",3);

		/* crea stanze del labirinto */
		Stanza atrio = new StanzaBloccata("Atrio","nord","lanterna");
		Stanza aulaN11 = new StanzaMagica("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new StanzaBuia("Laboratorio Campus","osso");
		Stanza biblioteca = new Stanza("Biblioteca");
		stanze.add(atrio);stanze.add(biblioteca);stanze.add(aulaN11);stanze.add(aulaN10);stanze.add(laboratorio);

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		laboratorio.addAttrezzo(cibo);
		
		AbstractPersonaggio p1=new Strega("Strega","Posso portarti in una stanza adiacente");
		AbstractPersonaggio p2=new Mago("Mago","Posso donarti un attrezzo!",new Attrezzo("spada",2));
		AbstractPersonaggio p3=new Cane("Cane","Occhio che mordo!");
		atrio.addPersonaggio(p1);
		aulaN11.addPersonaggio(p2);
		laboratorio.addPersonaggio(p3);
		
		// il gioco comincia nell'atrio
		stanzaCorrente = atrio;  
		stanzaVincente = biblioteca;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCor) {
		stanzaCorrente = stanzaCor;
	}

	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}
}