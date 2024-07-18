// Arquivo: TaxaDiaria.java
package sistemaInterno;

public class TaxaDiaria extends Taxas {
    private static final long serialVersionUID = 1L;
    private static final double TAXA_DIARIA = 0.1; // 10%

    public TaxaDiaria() {
    }

    public TaxaDiaria(double valorBase) {
        super(valorBase);
    }

    @Override
    public double calcularTaxa() {
        return valorBase * TAXA_DIARIA;
    }

    @Override
    public void receberTaxa() {
        BancoEstacionamento.adicionarTaxa("Taxa Diária", calcularTaxa());
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Taxa Diária:");
        super.exibirDetalhes();
    }
}
