package dadosEstacionamento;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import entidades.PedidoEstacionar;
import estacionamentos.Estacao;
import estacionamentos.Filiais;

public class Garagem {

	private static Estacao estacao;
	private int totalCarros;
	private static PedidoEstacionar pedido;

	private static Scanner sc = new Scanner(System.in);

	public Garagem(int totalCarros) {
		this.totalCarros = totalCarros;

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

	public void lerEstacionamento() throws IllegalArgumentException {
		for (PedidoEstacionar carro : listaCarros()) {
			System.out.println("Estacionado: " + carro.getCarro().getModelo() + ": " + carro.getVaga());
		}
	}

	public static Integer lerVagas(String vaga) {
		char letra = vaga.charAt(0);
		int posicao = Integer.parseInt(vaga.substring(1));
		if (posicao == 0) {
			// minúsculas
			throw new IllegalArgumentException("As vagas começam da posição 1.");
		} else if (vaga.charAt(0) > 96 && vaga.charAt(0) < 123 && posicao < 20) {
			return (int) letra - 97 + posicao - 1;

		} else if (vaga.charAt(0) > 64 && vaga.charAt(0) < 91 && posicao < 20) {
			return (int) letra - 65 + posicao + 539 - 1;
		} else {
			throw new IllegalArgumentException("Vaga fora de range.");
		}
		// cada letra simboliza o acréscimo de 20, ex a00 será vaga index 0, vaga b02
		// será vaga b=20 + 02 index 22
		// limite: letra minúscula

	}

	public static String showVagas(int code) {
		int fmtCode = (code / 20) + 97;
		// recebendo letra e número
		return "" + (char) fmtCode + "" + (code - fmtCode);
		// cada letra simboliza o acréscimo de 20, ex a00 será vaga index 0, vaga b02
		// será vaga b=20 + 02 index 22
		// limite: letra minúsculo
	}

	public void emitirNota() {
		System.out.printf("Veículo retirado da garagem, placa: %s, modelo: %s, data: %s\n",
				getPedido().getCarro().getPlaca(), getPedido().getCarro().getModelo(), LocalDateTime.now().toString());
	}

	public void printarVagas(Filiais filial) {
		int contador = 0;
		System.out.println("Vagas agendadas");
		for (PedidoEstacionar vaga : filial.getVagasAgendadas()) {
			System.out.println(vaga);
			if (contador % 10 == 0) {
				System.out.println();
			}
		}
		System.out.println("Vagas flexíveis");
		for (PedidoEstacionar vaga : filial.getVagasFlex()) {
			System.out.println(vaga);
			if (contador % 10 == 0) {
				System.out.println();
			}
		}
	}

	public Long checarPrazo(PedidoEstacionar pedido, int opcao) {
		if (pedido.getFinish() == null) {
			return null;
		}
		switch (opcao) {
		case 1:
			// minutos
			// se get finish ainda não tiver incializado, tratar
			return Duration.between(pedido.getStart(), pedido.getFinish()).toMinutes();
		case 2:
			// horas
			return Duration.between(pedido.getStart(), pedido.getFinish()).toHours();
		case 3:
			// dias
			return Duration.between(pedido.getStart(), pedido.getFinish()).toDays();
		default:
			throw new IllegalArgumentException("Opção de formatação de tempo indefinida");
		}
	}

	public void checarVencimentos() {

	}

	public void atualizarSistema() {
		// checar se algum carro passou do prazo de vencimento
	}

	public void finalizarPedido(PedidoEstacionar pedido) {
		pedido.setFinish(LocalDateTime.now());
		// validar
	}

	// Recebendo código formatado do lugar desejado
	public void editarVagas(Filiais filial) {
		boolean fimOp = false;
		do {
			try {
				
			
			if (true) {
				// desocupar/ocupar vaga
				System.out.println("Digite a vaga a ser preenchida: ");
				String vaga = sc.next();
				// metodo para validar vada
				int code = Garagem.lerVagas(vaga);
				System.out.println(
						"1 - Informe a placa do veículo para ocupar\n2 - Pressione enter para desocupar\n3 - Voltar");
				String placa = sc.next();
				if (true)// vaga agendada
					filial.getTotalVagas()[0].set(code, null);
				else if (false) {
					// vaga flex
				} else {
					throw new IllegalArgumentException("Vaga não encontrada");
				}
			} else if (false) {
				// encontrar vaga por placa// realocar pedido para outra vaga
				System.out.println("Digite a placa do veículo para localizarmos no sistema");
				String placa = sc.next();
				String vaga = filial.searchPlate(placa);
				System.out.println("O pedido está na vaga: " + vaga);
				
				
				
				System.out.println("1 - Realocar pedido para outra vaga\n2- Voltar");
				if (true)// vaga agendada
					filial.getTotalVagas()[0].set(code, null);
				else if (false) {
					// vaga flex
					filial.getTotalVagas()[1].set(searchPlate.get, null);
				} else {
					throw new IllegalArgumentException("Vaga não encontrada");
				}
				
				
				
				
				System.out.println("Digite a vaga a ser preenchida: ");
				// if status da vaga for a mesma que a do carro e se a vaga não tiver um carro.
				String vaga = sc.next();
				int index = Garagem.lerVagas(vaga);
				filial.getTotalVagas()[1].set(index, pedido);
				System.out.println("Voltando...");
				Thread.sleep(3000);

			} else {

			}
			fimOp = true;
			} catch (InputMismatchException e) {
				
			}
		} while (!fimOp);

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

	/*
	 * private Double pricePerDay; private Double pricePerHour; private Taxas
	 * taxService;
	 * 
	 * public void processInvoice(PedidoEstacionar pedido) {
	 * 
	 * double minutes = ChronoUnit.MINUTES.between(pedido.getStart(),
	 * pedido.getFinish()); double hours = minutes / 60;
	 * 
	 * double basicPayment; if (hours <= 12.0) { basicPayment = pricePerHour *
	 * Math.ceil(hours); } else { basicPayment = pricePerDay *
	 * Math.ceil(hours/24.0); }
	 * 
	 * double tax = taxService.tax(basicPayment);
	 * 
	 * pedido.setInvoice(new Divida(basicPayment, tax)); }
	 */
}
