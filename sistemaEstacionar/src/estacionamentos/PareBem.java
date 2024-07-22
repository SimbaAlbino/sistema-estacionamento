package estacionamentos;

import java.util.ArrayList;
import java.util.Collections;

import entidades.PedidoEstacionar;

public class PareBem extends Filiais {

	private int totalVagasAge = 10;
	private int totalVagasFlex = 50;
	private ArrayList<PedidoEstacionar> vagasAgendadas =  new ArrayList<>(Collections.nCopies(totalVagasAge, (PedidoEstacionar) null));
	private ArrayList<PedidoEstacionar> vagasFlex = new ArrayList<>(Collections.nCopies(totalVagasAge, (PedidoEstacionar) null));

	public PareBem(PedidoEstacionar pedido) {
		super(pedido);
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

}
