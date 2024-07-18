// Arquivo: TaxaAnual.java
package sistemaInterno;

public class TaxaAnual extends Taxas {
	private static final long serialVersionUID = 1L;
	private static final double TAXA_ANUAL = 0.02; // 2%

	public TaxaAnual() {
	}

	public TaxaAnual(double valorBase) {
		super(valorBase);
	}

	@Override
	public double calcularTaxa() {
		return valorBase * TAXA_ANUAL;
	}

	@Override
	public void receberTaxa() {
		BancoEstacionamento.adicionarTaxa("Taxa Anual", calcularTaxa());
	}

	@Override
	public void exibirDetalhes() {
		System.out.println("Taxa Anual:");
		super.exibirDetalhes();
	}
}
