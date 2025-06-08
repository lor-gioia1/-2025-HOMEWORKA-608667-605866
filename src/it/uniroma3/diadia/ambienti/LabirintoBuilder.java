//package it.uniroma3.diadia.ambienti;
//
//import it.uniroma3.diadia.attrezzi.Attrezzo;
//import java.util.*;
//
//public class LabirintoBuilder extends Labirinto {
//
//	private Stanza stanzaVincente;
//	private Stanza stanzaIniziale;
//	private Set<Stanza> stanze;
//	private Stanza ultima;
//	
//	
//	public LabirintoBuilder() {
//		this.stanze=new HashSet<>();
//	}
//	
//	public LabirintoBuilder addStanzaVincente(String nome) {
//		this.stanzaVincente=new Stanza(nome);
//		this.ultima=stanzaVincente;
//		stanze.add(stanzaVincente);
//		return this;
//	}
//	
//	public LabirintoBuilder addStanzaVincente(Stanza s) {
//		this.stanzaVincente=s;
//		this.ultima=stanzaVincente;
//		stanze.add(stanzaVincente);
//		return this;
//	}
//	
//	public Set<Stanza> getStanze(){
//		return this.stanze;
//	}
//	
//	public LabirintoBuilder addStanzaIniziale(String nome) {
//		this.stanzaIniziale=new Stanza(nome);
//		this.ultima=stanzaIniziale;
//		stanze.add(stanzaIniziale);
//		return this;
//	}
//	
//	public LabirintoBuilder addStanzaIniziale(Stanza s) {
//		this.stanzaIniziale=s;
//		this.ultima=stanzaIniziale;
//		stanze.add(stanzaIniziale);
//		return this;
//	}
//	
//	public LabirintoBuilder addStanza(String nome) {
//		Stanza s=new Stanza(nome);
//		stanze.add(s);
//		this.ultima=s;
//		return this;
//	}
//	
//	public LabirintoBuilder addStanza(Stanza s) {
//		stanze.add(s);
//		this.ultima=s;
//		return this;
//	}
//	
//	public Stanza getStanzaInizialeLab() {
//		return this.stanzaIniziale;
//	}
//	
//	public Stanza getStanzaVincenteLab() {
//		return this.stanzaVincente;
//	}
//	
//	public LabirintoBuilder addAttrezzo(String nome,int peso) {
//		this.ultima.addAttrezzo(new Attrezzo(nome,peso));
//		return this;
//	}
//	
//	public Stanza hasStanza(String nome) {
//		for(Stanza s : this.stanze) {
//			if(s.getNome().equals(nome))
//				return s;
//		}
//		return null;
//	}
//	
//	public LabirintoBuilder addAdiacenza(String nome1, String nome2, String direzione) {
//		Stanza s1=this.hasStanza(nome1);
//		Stanza s2=this.hasStanza(nome2);
//		if(s1!=null&&s2!=null) {
//			s1.impostaStanzaAdiacente(direzione,s2);
//		}
//		return this;
//	}
//	
//	public Labirinto getLabirinto() {
//		if(this.stanzaIniziale==null&&!this.stanze.isEmpty()) {
//			Iterator<Stanza>it=this.stanze.iterator();
//			this.stanzaIniziale=it.next();
//		}
//		if(this.stanzaVincente==null&&!this.stanze.isEmpty()) {
//			Iterator<Stanza>it=this.stanze.iterator();
//			this.stanzaVincente=it.next();
//		}
//		return new Labirinto(this);
//	}
//}
