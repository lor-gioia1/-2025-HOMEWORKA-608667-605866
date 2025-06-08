package it.uniroma3.diadia;

import java.util.*;

public class IOSimulator implements IO{
	private List<String> messaggiStampati;
	private List<String> istruzioni;
	private Iterator<String>it1;
	
	public IOSimulator(String[] input) {
		this.istruzioni=new ArrayList<String>(Arrays.asList(input));
		this.messaggiStampati=new ArrayList<String>();
		this.it1=istruzioni.iterator();
	}
	
	@Override
	public String leggiRiga() {
		if(!this.it1.hasNext())
			return "No";
		return this.it1.next();
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		this.messaggiStampati.add(msg);
	}
	
	public void getIstruzione () {
		for (String s : this.istruzioni) {
			if(s!=null)
				System.out.println(s+" ");
		}
	}
	
	public String getMessaggio (int indice) {
		if (indice < 0 || indice >= messaggiStampati.size()||this.messaggiStampati.get(indice)==null)
			return "Nessun messaggio";
		return this.messaggiStampati.get(indice);
	}
	
	public Scanner getScan() {return null;}
}
