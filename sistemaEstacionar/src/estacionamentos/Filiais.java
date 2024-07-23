package estacionamentos;

import java.util.ArrayList;

import dadosEstacionamento.Garagem;
import dadosEstacionamento.VagaCarro;
import entidades.PedidoEstacionar;
import sistemaInterno.Taxas;

public abstract class Filiais {

	private PedidoEstacionar pedido;
	private Taxas taxa;

	public Filiais(PedidoEstacionar pedido) {
		this.pedido = pedido;
	}

	public Filiais() {
	}

	public PedidoEstacionar getPedido() {
		return pedido;
	}

	public void setPedido(PedidoEstacionar pedido) {
		this.pedido = pedido;
	}

	public abstract ArrayList<PedidoEstacionar> getVagasAgendadas();

	public abstract ArrayList<PedidoEstacionar> getVagasFlex();

	public ArrayList<PedidoEstacionar>[] getTotalVagas() {
		// 0 para vagas agendadas, 1 para vagas flexíveis
		@SuppressWarnings("unchecked")
		ArrayList<PedidoEstacionar>[] vagasTotais = new ArrayList[] { getVagasAgendadas(), getVagasFlex() };
		return vagasTotais;
	}

	public String searchPlate(String placa) {
		PedidoEstacionar pedido = PedidoEstacionar.getPedidoPlaca(placa);
		int index = 0;
		if (pedido.getVagaCarro() == VagaCarro.Vaga_Agendada) {
			index = getTotalVagas()[0].indexOf(PedidoEstacionar.getPedidoPlaca());
		} else {
			index = getTotalVagas()[1].indexOf(PedidoEstacionar.getPedidoPlaca());
		}
		if (index == -1) {
			throw new IllegalArgumentException("Veículo não encontrado no estacionamento");
		}
		return Garagem.showVagas(index);
	}

	public void mostrarStatusLista(VagaCarro tipoVaga) {
		ArrayList<PedidoEstacionar> vagasTotais = new ArrayList<>();
		if (tipoVaga == VagaCarro.Vaga_Flexivel) {
			vagasTotais = getTotalVagas()[1];
			System.out.println("Vagas livres disponíveis: \n");
		} else if (tipoVaga == VagaCarro.Vaga_Agendada) {
			System.out.println("Vagas por agendamento disponíveis: \n");
			vagasTotais = getTotalVagas()[0];
		}
		for (int i = 0; i < vagasTotais.size(); i++) {
			if (vagasTotais.get(i) == null) {
				System.out.println("Vaga " + (i + 1) + ": null");
			} else {
				System.out.println("Vaga " + (i + 1) + ": ocupado");
			}
		}
	}

	public abstract boolean ocuparVaga(int vaga, PedidoEstacionar pedido);
	/*
	 * if (//o carro não estiver em uma vaga) {
	 * 
	 * }
	 */

	public abstract boolean desocuparVaga(int vaga);
	/*
	 * if (//o carro não estiver em uma vaga) {
	 * 
	 * }
	 */

}
