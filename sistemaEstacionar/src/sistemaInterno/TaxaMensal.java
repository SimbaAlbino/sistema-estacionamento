// Arquivo: TaxaMensal.java
package sistemaInterno;

public class TaxaMensal extends Taxas {
	private static final long serialVersionUID = 1L;
	private static final double TAXA_MENSAL = 0.05; // 5%

	public TaxaMensal() {
	}

	public TaxaMensal(double valorBase) {
		super(valorBase);
	}

	@Override
	public double calcularTaxa() {
		return valorBase * TAXA_MENSAL;
	}

	@Override
	public void receberTaxa() {
		BancoEstacionamento.adicionarTaxa("Taxa Mensal", calcularTaxa());
	}

	@Override
	public void exibirDetalhes() {
		System.out.println("Taxa Mensal:");
		super.exibirDetalhes();
	}
}
