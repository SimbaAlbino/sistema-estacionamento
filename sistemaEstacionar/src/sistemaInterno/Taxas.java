// Arquivo: Taxas.java
package sistemaInterno;

import java.io.Serializable;

public abstract class Taxas implements Serializable {
	private static final long serialVersionUID = 1L;
	protected double valorBase;

	public Taxas() {
	}

	public Taxas(double valorBase) {
		this.valorBase = valorBase;
	}

	public double getValorBase() {
		return valorBase;
	}

	public void setValorBase(double valorBase) {
		this.valorBase = valorBase;
	}

	public abstract double calcularTaxa();

	public abstract void receberTaxa();

	public void exibirDetalhes() {
		System.out.println("Valor Base: " + valorBase);
		System.out.println("Taxa Calculada: " + calcularTaxa());
	}
}
