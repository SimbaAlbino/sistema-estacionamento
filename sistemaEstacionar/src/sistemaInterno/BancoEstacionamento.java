// Arquivo: BancoEstacionamento.java
package sistemaInterno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoEstacionamento {
    private int numEnvio;
    private String tipoEnvio;
    private int custoEnvio;
    private int numRegiaoEnvio;
    private static double saldoTotalBanco;
    private static List<String[]> historicoPagamentos = new ArrayList<>();
    private static Map<String, Double> taxasMap = new HashMap<>();
    private static String caminhoBanco = "C:\\caminho\\para\\o\\bancoEstacionamento.txt";

    public BancoEstacionamento() {
    }

    public static void adicionarTaxa(String chave, double valor) {
        taxasMap.put(chave, taxasMap.getOrDefault(chave, 0.0) + valor);
        serializarBanco();
    }

    public static double calcularTaxaGeral() {
        return taxasMap.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public static String detalharTaxas() {
        StringBuilder detalhes = new StringBuilder();
        for (Map.Entry<String, Double> entry : taxasMap.entrySet()) {
            detalhes.append(String.format("%s: %.2f\n", entry.getKey(), entry.getValue()));
        }
        return detalhes.toString();
    }

    public static void adicionarHistoricoPagamento(String[] vetorTaxasCalc) {
        historicoPagamentos.add(vetorTaxasCalc);
        serializarBanco();
    }

    public static List<String[]> listarHistoricoPagamentos() {
        return desserializarBanco();
    }

    public static void calcularSaldoTotal() {
        saldoTotalBanco = 0;

        for (String[] pagamento : historicoPagamentos) {
            if (pagamento.length > 4) {
                try {
                    double valor = Double.parseDouble(pagamento[4]);
                    saldoTotalBanco += valor;
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao converter valor para double: " + e.getMessage());
                }
            }
        }
    }

    public static double getSaldoTotalBanco() {
        return saldoTotalBanco;
    }

    public static void exibirHistoricoPagamentos() {
        System.out.println("\nHistórico de Pagamentos:");
        for (String[] registro : listarHistoricoPagamentos()) {
            printarRegistro(registro);
        }
    }

    public static void operacaoFuncionario() {
        exibirHistoricoPagamentos();
        System.out.println("Saldo Total do Banco: " + getSaldoTotalBanco());
        System.out.println("Detalhes das Taxas:");
        System.out.println(detalharTaxas());
    }

    public static void liberarPedido(String nomeCliente) {
        System.out.println("Pedido liberado para o cliente: " + nomeCliente);
    }

    public void atualizarInfoEnvio(int numEnvio, String tipoEnvio, int custoEnvio, int numRegiaoEnvio) {
        this.numEnvio = numEnvio;
        this.tipoEnvio = tipoEnvio;
        this.custoEnvio = custoEnvio;
        this.numRegiaoEnvio = numRegiaoEnvio;
    }

    private static void serializarBanco() {
        // Implementar a lógica de serialização para salvar o estado do banco
    }

    private static List<String[]> desserializarBanco() {
        // Implementar a lógica de desserialização para carregar o estado do banco
        return new ArrayList<>();
    }

    private static void printarRegistro(String[] registro) {
        System.out.println("Cliente: " + registro[0]);
        System.out.println("Taxa Diária: " + registro[1]);
        System.out.println("Taxa Mensal: " + registro[2]);
        System.out.println("Taxa Anual: " + registro[3]);
        System.out.println("Total: " + registro[4]);
        System.out.println();
    }
}
