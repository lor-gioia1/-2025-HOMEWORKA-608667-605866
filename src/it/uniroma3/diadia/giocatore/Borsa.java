package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerNome;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;
import java.util.*;
import java.io.*;
import java.util.Properties;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private List<Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<>();
	}

	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}
	
	public int defaultValue() {
		Properties prop = new Properties();
		int r=0;
		try(FileReader read=new FileReader("diadia.properties")){
			prop.load(read);
			String ric=prop.getProperty("peso_max_borsa");
			r=Integer.valueOf(ric).intValue();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.add(attrezzo);
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator <Attrezzo> it = attrezzi.iterator();
		while(it.hasNext()) {
			Attrezzo temp=it.next();
			if (temp.getNome().equals(nomeAttrezzo))
				a = temp;
		}
		return a;
	}

	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : attrezzi)
			peso += a.getPeso();
		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;

		for(Attrezzo b : attrezzi) {
			if(b.getNome().equals(nomeAttrezzo)){
				a=b;
			}
		}
		this.attrezzi.remove(a);
		return a;
	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> ordinata=new ArrayList<>(this.attrezzi);
		ComparatoreAttrezziPerPeso cmp=new ComparatoreAttrezziPerPeso();
		Collections.sort(ordinata,cmp);
		return ordinata;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		ComparatoreAttrezziPerNome cmp=new ComparatoreAttrezziPerNome();
		SortedSet<Attrezzo> ordinato=new TreeSet<Attrezzo>(cmp);
		for(Attrezzo a : this.attrezzi) {
			ordinato.add(a);
		}
		return ordinato;
	}

	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Set <Attrezzo> temp;
		Map<Integer,Set<Attrezzo>> ordinata=new TreeMap<Integer,Set<Attrezzo>>();
		if (this.attrezzi==null)
			return ordinata;
		for(Attrezzo a : this.attrezzi) {
			temp=ordinata.get(a.getPeso());
			if(temp==null) {
				temp=new TreeSet<Attrezzo>(new ComparatoreAttrezziPerPeso());
				temp.add(a);
				ordinata.put(a.getPeso(),temp);
			}
			else {
				temp.add(a);
			}
		}
		return  ordinata;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> ordinato=new TreeSet<Attrezzo>(new ComparatoreAttrezziPerPeso());
		for(Attrezzo a : this.attrezzi)
			ordinato.add(a);
		return ordinato;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		SortedSet<Attrezzo> ordinato=this.getContenutoOrdinatoPerNome();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo a : ordinato)
				s.append(a.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}