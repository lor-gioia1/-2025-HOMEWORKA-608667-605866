package it.uniroma3.diadia;

public enum Stringhe {
	Iniziale{
		@Override
		public String getStringa() {
			return(""+
					"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
					"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
					"I locali sono popolati da strani personaggi, " +
					"alcuni amici, altri... chissa!\n"+
					"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
					"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
					"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
					"Per conoscere le istruzioni usa il comando 'aiuto'.");
		}
	}, Vittoria{
		@Override
		public String getStringa() {
			return "Hai vinto";
		}
	}, Sconfitta{
		@Override
		public String getStringa() {
			return "Hai perso!";
		}
	}, ComandoNonValido{
		@Override
		public String getStringa() {
			return "Comando non valido";
		}
	};
	
	public abstract String getStringa();
}
