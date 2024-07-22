package estacionamentos;

import java.util.ArrayList;

import entidades.PedidoEstacionar;

public abstract class Filiais {
	
	
	PedidoEstacionar pedido;
	
	public Filiais(PedidoEstacionar pedido) {
		this.pedido = pedido;
	}
	
	public abstract ArrayList<PedidoEstacionar> getVagasAgendadas();
	
	public abstract ArrayList<PedidoEstacionar> getVagasFlex();
	
	public void ocuparVaga(int vaga) {
		/*
		if (//o carro não estiver em uma vaga) {
			
		}
		*/
	}
	
	public void desocuparVaga(int vaga) {
		/*
		if (//o carro não estiver em uma vaga) {
			
		}
		*/
	}
	
}
