package it.uniroma3.diadia;

public class IOSimulator implements IO{
	private String[] messaggiStampati;
	private String[] istruzioni;
	private int contatore1;
	private int contatore2;
	
	public IOSimulator(String[] input) {
		this.istruzioni=input;
		this.messaggiStampati=new String[50];
		contatore1=0;
		contatore2=0;
	}
	
	@Override
	public String leggiRiga() {
		if(contatore1 >= istruzioni.length || istruzioni[contatore1] == null)
			return "No";
		String a = this.istruzioni[contatore1];
		contatore1++;
		return a;
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		if(contatore2>=messaggiStampati.length)
			return;
		messaggiStampati[contatore2]=msg;
		contatore2++;
	}
	
	public void getIstruzione () {
		for (int i=0;i<istruzioni.length;i++) {
			if(istruzioni[i]!=null)
				System.out.println(istruzioni[i]+" ");
		}
	}
	
//	public void setIstruzione(String cmd) {
//		if(istruzioni.length==0||contatore3>=istruzioni.length)
//			return;
//		istruzioni[contatore3]=cmd;
//		contatore3++;
//	}
	
	public String getMessaggio (int indice) {
		if (indice < 0 || indice >= messaggiStampati.length || messaggiStampati[indice] == null)
			return "Nessun messaggio";
		return messaggiStampati[indice];
	}
}
