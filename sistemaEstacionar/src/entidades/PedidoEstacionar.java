package entidades;

import dadosEstacionamento.StatusCarro;
import dadosEstacionamento.VagaCarro;

public class PedidoEstacionar {
	private Carro carro;
	private String vaga;
	private StatusCarro statusCarro;
	private VagaCarro vagaCarro;

	public PedidoEstacionar(Carro carro, String vaga, StatusCarro statusCarro, VagaCarro vagaCarro) {
		this.carro = carro;
		this.vaga = vaga;
		this.statusCarro = statusCarro;
		this.vagaCarro = vagaCarro;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public String getVaga() {
		return vaga;
	}

	public void setVaga(String vaga) {
		this.vaga = vaga;
	}

	public StatusCarro getStatusCarro() {
		return statusCarro;
	}

	public void setStatusCarro(StatusCarro statusCarro) {
		this.statusCarro = statusCarro;
	}

	public VagaCarro getVagaCarro() {
		return vagaCarro;
	}

	public void setVagaCarro(VagaCarro vagaCarro) {
		this.vagaCarro = vagaCarro;
	}

}
