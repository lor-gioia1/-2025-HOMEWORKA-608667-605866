package it.uniroma3.diadia.giocatore;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Giocatore {

	private Borsa borsa;

	final private int CFU_INIZIALI = this.defaultValue();
	private int cfu;

	public Giocatore() {
		this.cfu=CFU_INIZIALI;
		this.borsa=new Borsa();
	}
	
	public int defaultValue() {
		Properties prop = new Properties();
		int r=0;
		try(FileReader read=new FileReader("diadia.properties")){
			prop.load(read);
			String ric=prop.getProperty("cfu_iniziali");
			r=Integer.valueOf(ric).intValue();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}

	public Borsa getBorsa() {
		return borsa;
	}

	public void removeCfu() {
		setCfu(--cfu);
	}

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
}