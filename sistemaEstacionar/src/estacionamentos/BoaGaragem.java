package estacionamentos;

import dadosEstacionamento.VagaAgendada;
import dadosEstacionamento.VagaFlex;

public class BoaGaragem extends Filiais {
	
	private int totalVagasAge = 10;
	private int totalVagasFlex = 50;
	private boolean[] vagasLivresAgendada = new boolean[totalVagasAge];
	private boolean[] vagasLivresFlex = new boolean[totalVagasFlex];
	
	public BoaGaragem(TaxService tax) {
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
