package dadosEstacionamento;

import java.time.LocalDateTime;
import java.util.ArrayList;

import aplicacao.ModelagemFile;
import entidades.PedidoEstacionar;
import estacionamentos.Estacao;

public class Garagem {
	
	private Estacao estacao;
	private int totalCarros;
	PedidoEstacionar pedido;
	
	public Garagem(Estacao estacao, PedidoEstacionar pedido) {
		this.estacao = estacao;
		this.pedido = pedido;
	}

	public ArrayList<PedidoEstacionar> listaCarros() {
		ArrayList<PedidoEstacionar> garagem = ModelagemFile.desserializar(getEstacao().getFileEmpresa());
		return garagem;
	}

	public void addCarro(PedidoEstacionar carro) {
		ArrayList<PedidoEstacionar> estoqueGeral = listaCarros();
		estoqueGeral.add(carro);
		ModelagemFile.serializar(getEstacao().getFileEmpresa(), estoqueGeral);
		//
	}

	public synchronized void removerCarro(PedidoEstacionar carro) {
		ArrayList<PedidoEstacionar> estoqueGeral = listaCarros();
		estoqueGeral.remove(carro);
		emitirNota();
		ModelagemFile.serializar(getEstacao().getFileEmpresa(), estoqueGeral);

	}
	
	 public void lerEstacionamento() {
	        for (PedidoEstacionar carro : listaCarros()) {
	            System.out.println("Estacionado: " + carro.getCarro().getModelo() + ": " + carro.getVaga());
	        }
	    }
	 
	 public void emitirNota() {
		 System.out.printf("Veículo retirado da garagem, placa: %s, modelo: %s, data: %s\n", getPedido().getCarro().getPlaca(), getPedido().getCarro().getModelo(), LocalDateTime.now().toString());
	 }

	public int getTotalCarros() {
		return totalCarros;
	}

	public void setTotalCarros(int totalCarros) {
		this.totalCarros = listaCarros().size();
	}

	public Estacao getEstacao() {
		return estacao;
	}

	public PedidoEstacionar getPedido() {
		return pedido;
	}

	public void setPedido(PedidoEstacionar pedido) {
		this.pedido = pedido;
	}
	
	
	
	
	 
	 
	
	/*
	private Double pricePerDay;
	private Double pricePerHour;
	private Taxas taxService;
	
	public void processInvoice(PedidoEstacionar pedido) {
		
		double minutes = ChronoUnit.MINUTES.between(pedido.getStart(), pedido.getFinish());
		double hours = minutes / 60;
		
		double basicPayment;
		if (hours <= 12.0) {
			basicPayment = pricePerHour * Math.ceil(hours);
		} else {
			basicPayment = pricePerDay * Math.ceil(hours/24.0);
		}
		
		double tax = taxService.tax(basicPayment);
		
		pedido.setInvoice(new Divida(basicPayment, tax));
	}
	    */
}
