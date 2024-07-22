package estacionamentos;

import sistemaInterno.Taxas;

public class BoaGaragem extends Filiais {
	
	private int totalVagasAge = 30;
	private int totalVagasFlex = 80;
	private boolean[] vagasLivresAgendada = new boolean[totalVagasAge];
	private boolean[] vagasLivresFlex = new boolean[totalVagasFlex];
	
	public BoaGaragem(Taxas tax) {
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
