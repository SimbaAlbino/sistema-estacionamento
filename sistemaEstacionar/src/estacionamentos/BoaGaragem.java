package estacionamentos;

import java.util.ArrayList;
import java.util.Collections;

import dadosEstacionamento.Garagem;
import entidades.PedidoEstacionar;

public class BoaGaragem extends Filiais {
    private int totalVagasAge = 30;
    private int totalVagasFlex = 80;
    private ArrayList<PedidoEstacionar> vagasAgendadas = new ArrayList<>(Collections.nCopies(totalVagasAge, null));
    private ArrayList<PedidoEstacionar> vagasFlex = new ArrayList<>(Collections.nCopies(totalVagasFlex, null));
    private double taxaFixaAdd = 5.0;
    private double valorDaDiaria = 20.0;

    public BoaGaragem(PedidoEstacionar pedido) {
        super(pedido);
    }
    
    public BoaGaragem() {
        super();
    }

    public int getTotalVagasAge() {
        return totalVagasAge;
    }

    public int getTotalVagasFlex() {
        return totalVagasFlex;
    }

    @Override
    public ArrayList<PedidoEstacionar> getVagasAgendadas() {
        return vagasAgendadas;
    }

    @Override
    public ArrayList<PedidoEstacionar> getVagasFlex() {
        return vagasFlex;
    }

    public double calcularValorTotalAgendamento() {
        Garagem garagem = new Garagem(); // Instanciar com valor fictício
        Long dias = garagem.checarPrazo(getPedido(), 3); // Calcula os dias
        if (dias == null) {
            return 0.0;
        }
        return (valorDaDiaria + taxaFixaAdd) * dias;
    }

    public void exibirDetalhes() {
        Garagem garagem = new Garagem(); // Instanciar com valor fictício
        Long dias = garagem.checarPrazo(getPedido(), 3); // Calcula os dias
        if (dias == null) {
            System.out.println("O prazo do pedido não foi definido.");
            return;
        }
        
        System.out.println("Estacionamento Boa Garagem");
        System.out.println("Taxa Fixa Adicional: " + taxaFixaAdd);
        System.out.println("Valor da Diária: " + valorDaDiaria);
        System.out.println("Valor Total do Agendamento por " + dias + " dias: " + calcularValorTotalAgendamento());
    }

    @Override
    public boolean ocuparVaga(int vaga, PedidoEstacionar pedido) {
        if (vaga < 0 || vaga >= totalVagasAge + totalVagasFlex) {
            return false; // Vaga fora do intervalo
        }

        if (vaga < totalVagasAge) { // Vaga agendada
            if (vagasAgendadas.get(vaga) == null) {
                vagasAgendadas.set(vaga, pedido);
                return true;
            }
        } else { // Vaga flexível
            int vagaFlex = vaga - totalVagasAge;
            if (vagasFlex.get(vagaFlex) == null) {
                vagasFlex.set(vagaFlex, pedido);
                return true;
            }
        }
        return false; // Vaga já ocupada
    }

    @Override
    public boolean desocuparVaga(int vaga) {
        if (vaga < 0 || vaga >= totalVagasAge + totalVagasFlex) {
            return false; // Vaga fora do intervalo
        }

        if (vaga < totalVagasAge) { // Vaga agendada
            if (vagasAgendadas.get(vaga) != null) {
                vagasAgendadas.set(vaga, null);
                return true;
            }
        } else { // Vaga flexível
            int vagaFlex = vaga - totalVagasAge;
            if (vagasFlex.get(vagaFlex) != null) {
                vagasFlex.set(vagaFlex, null);
                return true;
            }
        }
        return false; // Vaga já está desocupada
    }

    @Override
    public String getFileEmpresa() {
        // Retornar o nome do arquivo específico da empresa ou caminho
        return "boa_garagem.dat"; // Exemplo de caminho para armazenamento
    }
}
