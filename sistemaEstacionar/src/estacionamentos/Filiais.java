package estacionamentos;

import dadosEstacionamento.VagaAgendada;
import dadosEstacionamento.VagaFlex;

public abstract class Filiais {
	
	private TaxService tax;
	
	public Filiais( TaxService tax) {
		this.tax = tax;
	}

	public VagaAgendada getVagaAgendada() {
		return vagaAgendada;
	}

	public void setVagaAgendada(VagaAgendada vagaAgendada) {
		this.vagaAgendada = vagaAgendada;
	}

	public VagaFlex getVagaFlex() {
		return vagaFlex;
	}

	public void setVagaFlex(VagaFlex vagaFlex) {
		this.vagaFlex = vagaFlex;
	}

	public TaxService getTax() {
		return tax;
	}

	public void setTax(TaxService tax) {
		this.tax = tax;
	}
	
	
}
