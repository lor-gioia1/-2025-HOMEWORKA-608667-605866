package it.uniroma3.diadia;

import java.io.*;
import java.util.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite: ";

	/* prefisso della riga contenente le specifiche dei personaggi nelle stanze nel formato <nomePersonaggio> <nomeStanza> */
	private static final String PERSONAGGI_MARKER = "Personaggi: ";

	private static final String STANZE_BUIE_MARKER = "Stanze buie: ";

	private static final String STANZE_BLOCCATE_MARKER = "Stanze bloccate: ";

	private static final String STANZE_MAGICHE_MARKER = "Stanze magiche: ";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;
	private Map<String, Stanza> nome2stanza;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(Reader reader) {
		this.nome2stanza = new HashMap<>();
		this.reader = new LineNumberReader(reader);
	}

	public Labirinto carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECaricaStanzeBuie();
			this.leggiECaricaStanzeBloccate();
			this.leggiECaricaStanzeMagiche();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiEImpostaPersonaggi();
			List<Stanza>lista=new ArrayList<>(nome2stanza.values());
			Labirinto lab=new Labirinto.LabirintoBuilder()
					.addStanzaIniziale(stanzaIniziale)
					.addStanzaVincente(stanzaVincente)
					.setStanze(lista)
					.getLabirinto();
			return lab;
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private void leggiEImpostaPersonaggi() throws FormatoFileNonValidoException{
		String nomiPers = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
		for(String nomePers : separaStringheAlleVirgole(nomiPers)) {
			String nomePersonaggio=null;
			String nomeStanza=null;
			try (Scanner scannerLinea = new Scanner(nomePers)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un personaggio."));
				nomePersonaggio = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza in cui collocare "+nomePersonaggio+"."));
				nomeStanza = scannerLinea.next();
			}				
			if(isStanzaValida(nomeStanza)) {
				try {
					String nomeClasse = "it.uniroma3.diadia.personaggi.";
					nomeClasse += Character.toUpperCase(nomePersonaggio.charAt(0));
					nomeClasse += nomePersonaggio.substring(1);
					AbstractPersonaggio pers = (AbstractPersonaggio)Class.forName(nomeClasse).getDeclaredConstructor().newInstance();
					this.nome2stanza.get(nomeStanza).addPersonaggio(pers);
				} catch (Exception e) {
					msgTerminazionePrecoce("il nome del personaggio (non valido)");
				}
			}
		}
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		try(Scanner scanner = new Scanner(string)){
			scanner.useDelimiter(",");
			while (scanner.hasNext())
				result.add(scanner.next().trim());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void leggiECaricaStanzeBuie() throws FormatoFileNonValidoException{
		String tutteStanze=this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String stanza : separaStringheAlleVirgole(tutteStanze)) {
			String nomeStanza=null;
			String nomeAttrezzo=null;
			try (Scanner scannerLinea = new Scanner(stanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo ricercato"+nomeAttrezzo+"."));
				nomeAttrezzo = scannerLinea.next();
				Stanza s=new StanzaBuia(nomeStanza,nomeAttrezzo);
				if(isStanzaValida(nomeStanza)) {
					if(this.stanzaIniziale.getNome().equals(nomeStanza))
						this.stanzaIniziale=s;
					if(this.stanzaVincente.getNome().equals(nomeStanza))
						this.stanzaVincente=s;	

					nome2stanza.remove(nomeStanza);
					nome2stanza.put(nomeStanza,s);

				}
			}
		}
	}

	private void leggiECaricaStanzeBloccate() throws FormatoFileNonValidoException{
		String tutteStanze=this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String stanza : separaStringheAlleVirgole(tutteStanze)) {
			String nomeStanza=null;
			String nomeDirezione=null;
			String nomeAttrezzo=null;
			try (Scanner scannerLinea = new Scanner(stanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della direzione bloccate"+nomeDirezione+"."));
				nomeDirezione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo sbloccante"+nomeAttrezzo+"."));
				nomeAttrezzo = scannerLinea.next();
				Stanza s=new StanzaBloccata(nomeStanza,nomeDirezione,nomeAttrezzo);
				if(isStanzaValida(nomeStanza)) {
					if(this.stanzaIniziale.getNome().equals(nomeStanza))
						this.stanzaIniziale=s;
					if(this.stanzaVincente.getNome().equals(nomeStanza))
						this.stanzaVincente=s;	

					nome2stanza.remove(nomeStanza);
					nome2stanza.put(nomeStanza,s);

				}
			}
		}
	}

	private void leggiECaricaStanzeMagiche() throws FormatoFileNonValidoException{
		String tutteStanze=this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String stanza : separaStringheAlleVirgole(tutteStanze)) {
			String nomeStanza=null;
			String numSoglia=null;
			try (Scanner scannerLinea = new Scanner(stanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la soglia della stanza magica "+numSoglia+"."));
				numSoglia = scannerLinea.next();
				int n=Integer.valueOf(numSoglia).intValue();
				Stanza s=new StanzaMagica(nomeStanza,n);
				if(isStanzaValida(nomeStanza)) {
					if(this.stanzaIniziale.getNome().equals(nomeStanza))
						this.stanzaIniziale=s;
					if(this.stanzaVincente.getNome().equals(nomeStanza))
						this.stanzaVincente=s;	

					nome2stanza.remove(nomeStanza);
					nome2stanza.put(nomeStanza,s);

				}
			}
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		List<String>lista=separaStringheAlleVirgole(specificheUscite);
		for(String s : lista) {
			try (Scanner scannerDiLinea = new Scanner(s)) {			
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();
					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			}
		} 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
