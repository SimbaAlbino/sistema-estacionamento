package estacionamentos;

import java.util.ArrayList;
import java.util.Collections;
import entidades.PedidoEstacionar;

public class BoaGaragem extends Filiais {
	private int totalVagasAge = 30;
	private int totalVagasFlex = 80;
	private ArrayList<PedidoEstacionar> vagasAgendadas = new ArrayList<>(Collections.nCopies(totalVagasAge, (PedidoEstacionar) null));
	private ArrayList<PedidoEstacionar> vagasFlex = new ArrayList<>(Collections.nCopies(totalVagasFlex, (PedidoEstacionar) null));
	private double taxaFixaAdd = 5.0;
	private double valorDaDiaria = 20.0;

	public BoaGaragem(PedidoEstacionar pedido) {
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
		System.out.println("Estacionamento Boa Garagem");
		System.out.println("Taxa Fixa Adicional: " + taxaFixaAdd);
		System.out.println("Valor da Diária: " + valorDaDiaria);
		System.out.println("Valor Total do Agendamento por " +  Garagem.checarPrazo(getPedido(), 3)+ " dias: " + calcularValorTotalAgendamento( Garagem.checarPrazo(getPedido(), 3)));
	}
}
