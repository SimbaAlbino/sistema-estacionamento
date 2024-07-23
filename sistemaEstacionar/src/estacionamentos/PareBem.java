// Arquivo: PareBem.java
package estacionamentos;

import java.util.ArrayList;
import java.util.Collections;
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

    @Override
    public int getTotalVagasAge() {
        return totalVagasAge;
    }

    @Override
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

    @Override
    public double calcularValorTotalAgendamento() {
        return (valorDaDiaria + taxaFixaAdd) *  Garagem.checarPrazo(getPedido(), 3);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Estacionamento Pare Bem");
        System.out.println("Taxa Fixa Adicional: " + taxaFixaAdd);
        System.out.println("Valor da Diária: " + valorDaDiaria);
        System.out.println("Valor Total do Agendamento por " +  Garagem.checarPrazo(getPedido(), 3) + " dias: " + calcularValorTotalAgendamento( Garagem.checarPrazo(getPedido(), 3)));
    }
}
