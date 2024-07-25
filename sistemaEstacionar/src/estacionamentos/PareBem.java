package estacionamentos;

import java.util.ArrayList;
import java.util.Collections;

import dadosEstacionamento.Garagem;
import entidades.PedidoEstacionar;

public class PareBem extends Filiais {
    private int totalVagasAge = 10;
    private int totalVagasFlex = 50;
    private ArrayList<PedidoEstacionar> vagasAgendadas = new ArrayList<>(Collections.nCopies(totalVagasAge, (PedidoEstacionar) null));
    private ArrayList<PedidoEstacionar> vagasFlex = new ArrayList<>(Collections.nCopies(totalVagasFlex, (PedidoEstacionar) null));
    private double taxaFixaAdd = 10.0;
    private double valorDaDiaria = 30.0;

    public PareBem(PedidoEstacionar pedido) {
        super(pedido);
    }
    
    public PareBem() {
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
        // Usar a instância de Garagem para checar o prazo
        Garagem garagem = new Garagem(); // Instanciar com valor fictício
        Long prazo = garagem.checarPrazo(getPedido(), 3);
        if (prazo == null) {
            return 0.0;
        }
        return (valorDaDiaria + taxaFixaAdd) * prazo;
    }

    public void exibirDetalhes() {
        System.out.println("Estacionamento Pare Bem");
        System.out.println("Taxa Fixa Adicional: " + taxaFixaAdd);
        System.out.println("Valor da Diária: " + valorDaDiaria);
        
        // Calcular o prazo e valor total de forma segura
        Garagem garagem = new Garagem(); // Instanciar com valor fictício
        Long prazo = garagem.checarPrazo(getPedido(), 3);
        if (prazo == null) {
            System.out.println("O prazo do pedido não foi definido.");
            return;
        }
        
        double valorTotal = (valorDaDiaria + taxaFixaAdd) * prazo;
        System.out.println("Valor Total do Agendamento por " + prazo + " dias: " + valorTotal);
    }

    @Override
    public String getFileEmpresa() {
        // Retornar o nome do arquivo específico da empresa ou caminho
        return "pare_bem.dat"; // Exemplo de caminho para armazenamento
    }

    @Override
    public boolean ocuparVaga(int vaga, PedidoEstacionar pedido) {
        if (vaga < 0 || vaga >= totalVagasAge + totalVagasFlex) {
            return false; // Vaga fora dos limites
        }
        
        if (vaga < totalVagasAge) {
            if (vagasAgendadas.get(vaga) == null) {
                vagasAgendadas.set(vaga, pedido);
                return true;
            }
        } else {
            int index = vaga - totalVagasAge;
            if (vagasFlex.get(index) == null) {
                vagasFlex.set(index, pedido);
                return true;
            }
        }
        
        return false; // Vaga já ocupada
    }

    @Override
    public boolean desocuparVaga(int vaga) {
        if (vaga < 0 || vaga >= totalVagasAge + totalVagasFlex) {
            return false; // Vaga fora dos limites
        }
        
        if (vaga < totalVagasAge) {
            if (vagasAgendadas.get(vaga) != null) {
                vagasAgendadas.set(vaga, null);
                return true;
            }
        } else {
            int index = vaga - totalVagasAge;
            if (vagasFlex.get(index) != null) {
                vagasFlex.set(index, null);
                return true;
            }
        }
        
        return false; // Vaga já está desocupada
    }
}
