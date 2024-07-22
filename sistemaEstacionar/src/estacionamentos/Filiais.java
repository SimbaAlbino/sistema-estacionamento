package estacionamentos;

import sistemaInterno.Taxas;

public abstract class Filiais {
	
	private Taxas tax;
	
	public Filiais(Taxas tax) {
		this.tax = tax;
	}
	
	public Taxas getTax() {
		return tax;
	}

	public void setTax(Taxas tax) {
		this.tax = tax;
	}
}
