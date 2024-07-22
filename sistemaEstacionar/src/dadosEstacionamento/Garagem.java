package dadosEstacionamento;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
	 
	 public Integer lerVagas(String vaga) {
		 char letra = vaga.charAt(0);
		 int posicao = Integer.parseInt(vaga.substring(1));
		 if (vaga.charAt(0) > 96 && vaga.charAt(0) < 123 && posicao < 20) {
			 return (int) letra - 97 + posicao;
			 //minúsculas
		 } else if (vaga.charAt(0) > 64 && vaga.charAt(0) < 91 && posicao < 20) {
			 return (int) letra - 65 + posicao + 539;
		 } else {
			 throw new IllegalArgumentException("Vaga fora de range.");
		 }
		 //cada letra simboliza o acréscimo de 20, ex a00 será vaga index 0, vaga b02 será vaga b=20 + 02 index 22
		 //limite: letra minúscula
		 
	 }
	 
	 public String showVagas(int code) {
		 int fmtCode = (code / 20) + 97;
		   // recebendo letra e número
		 return "" + (char) fmtCode + "" + (code - fmtCode);
		 //cada letra simboliza o acréscimo de 20, ex a00 será vaga index 0, vaga b02 será vaga b=20 + 02 index 22
		 //limite: letra minúsculo
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
