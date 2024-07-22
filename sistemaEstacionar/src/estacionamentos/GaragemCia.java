package estacionamentos;

import sistemaInterno.Taxas;

public class GaragemCia extends Filiais {
	
	private int totalVagasAge = 20;
	private int totalVagasFlex = 60;
	private boolean[] vagasLivresAgendada = new boolean[totalVagasAge];
	private boolean[] vagasLivresFlex = new boolean[totalVagasFlex];
	
	public GaragemCia(Taxas tax) {
		super(tax);
	}

	public int getTotalVagasAge() {
		return totalVagasAge;
	}

	public int getTotalVagasFlex() {
		return totalVagasFlex;
	}

	public boolean[] getVagasLivresAgendada() {
		return vagasLivresAgendada;
	}

	public boolean[] getVagasLivresFlex() {
		return vagasLivresFlex;
	}
	
	
	
}
