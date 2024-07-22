package estacionamentos;

import java.util.ArrayList;
import java.util.Collections;

import entidades.PedidoEstacionar;

public class GaragemCia extends Filiais {

	private int totalVagasAge = 20;
	private int totalVagasFlex = 60;
	private ArrayList<PedidoEstacionar> vagasAgendadas = new ArrayList<>(Collections.nCopies(totalVagasAge, (PedidoEstacionar) null));
	private ArrayList<PedidoEstacionar> vagasFlex =  new ArrayList<>(Collections.nCopies(totalVagasAge, (PedidoEstacionar) null));

	public GaragemCia(PedidoEstacionar pedido) {
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
