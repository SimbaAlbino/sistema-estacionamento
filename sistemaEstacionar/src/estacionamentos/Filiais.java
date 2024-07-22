package estacionamentos;

import java.util.ArrayList;

import entidades.PedidoEstacionar;

public abstract class Filiais {
	
	
	PedidoEstacionar pedido;
	
	public Filiais(PedidoEstacionar pedido) {
		this.pedido = pedido;
	}
	
	public abstract ArrayList<PedidoEstacionar> getVagasLivresAgendada();
	
	public abstract ArrayList<PedidoEstacionar> getVagasLivresFlex();
	
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
