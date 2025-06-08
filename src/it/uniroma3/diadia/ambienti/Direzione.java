package it.uniroma3.diadia.ambienti;

public enum Direzione {

	nord(0){
		@Override
		public Direzione opposta() {return sud;}
	},
	est(90){
		@Override
		public Direzione opposta() {return ovest;}
	},
	sud(180){
		@Override
		public Direzione opposta() {return nord;}
	},
	ovest(270){
		@Override
		public Direzione opposta() {return est;}
	};
	
	private int gradi;
	
	private Direzione(int gradi) {
		this.gradi=gradi;
	}
	
	public int getGradi() {return this.gradi;}
	abstract public Direzione opposta();
}
