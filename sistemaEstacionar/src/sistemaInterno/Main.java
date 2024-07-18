// Arquivo: Main.java
package sistemaInterno;

public class Main {
	public static void main(String[] args) {
		Taxas taxaDiaria = new TaxaDiaria(100);
		Taxas taxaMensal = new TaxaMensal(1000);
		Taxas taxaAnual = new TaxaAnual(12000);

		String[] registro1 = { "Cliente1", String.format("%.2f", taxaDiaria.calcularTaxa()),
				String.format("%.2f", taxaMensal.calcularTaxa()), String.format("%.2f", taxaAnual.calcularTaxa()),
				String.format("%.2f",
						taxaDiaria.calcularTaxa() + taxaMensal.calcularTaxa() + taxaAnual.calcularTaxa()) };

		BancoEstacionamento.adicionarHistoricoPagamento(registro1);

		taxaDiaria.exibirDetalhes();
		taxaDiaria.receberTaxa();
		System.out.println();

		taxaMensal.exibirDetalhes();
		taxaMensal.receberTaxa();
		System.out.println();

		taxaAnual.exibirDetalhes();
		taxaAnual.receberTaxa();

		BancoEstacionamento.operacaoFuncionario();
	}
}
