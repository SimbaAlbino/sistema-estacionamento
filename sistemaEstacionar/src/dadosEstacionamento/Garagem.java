package dadosEstacionamento;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import aplicacao.UI;
import entidades.PedidoEstacionar;
import entidades.ValidarDados;
import estacionamentos.Filiais;

public class Garagem {

	private Filiais filiais;
	private static int totalCarros;
	private PedidoEstacionar pedido;

	// Caminho dos arquivos
	private static final String CAMINHO_BASE = "C:\\Users\\pedro\\Desktop\\Study\\sistema-estacionamento\\files\\";
	private static final String CAMINHO_PEDIDOS = CAMINHO_BASE + "pedidos.txt";

	private static Scanner sc = new Scanner(System.in);

	// Construtores
	public Garagem(Filiais filiais, PedidoEstacionar pedido) {
		this.filiais = filiais;
		this.pedido = pedido;
	}

	public Garagem(PedidoEstacionar pedido) {
		this.pedido = pedido;
	}
	
	public Garagem(Filiais filial) {
		this.filiais = filial;
	}

	public Garagem() {
	}

	// Caminho do arquivo
	private String getFilePath() {
		return CAMINHO_PEDIDOS;
	}

	// Lista de carros
	public ArrayList<PedidoEstacionar> listaCarros() {
		return ModelagemFile.desserializar(getFilePath());
	}

	// Adicionar carro
	public void addCarro(PedidoEstacionar carro) {
		ArrayList<PedidoEstacionar> estoqueGeral = listaCarros();
		estoqueGeral.add(carro);
		ModelagemFile.serializar(getFilePath(), estoqueGeral);
	}

	// Remover carro
	public synchronized void removerCarro(PedidoEstacionar carro) {
		ArrayList<PedidoEstacionar> estoqueGeral = listaCarros();
		estoqueGeral.remove(carro);
		emitirNota();
		ModelagemFile.serializar(getFilePath(), estoqueGeral);
	}

	// Ler estacionamento
	public void lerEstacionamento() {
		for (PedidoEstacionar carro : listaCarros()) {
			System.out.println("Estacionado: " + carro.getCarro().getModelo() + ": " + carro.getVaga());
		}
	}

	// Ler vagas
	public static Integer lerVagas(String vaga) {
		char letra = vaga.charAt(0);
		int posicao = Integer.parseInt(vaga.substring(1));
		if (posicao < 1) {
			throw new IllegalArgumentException("As vagas começam da posição 1.");
		} else if (Character.isLowerCase(letra) && posicao < 20) {
			return (int) letra - 97 + posicao - 1;
		} else if (Character.isUpperCase(letra) && posicao < 20) {
			return (int) letra - 65 + posicao + 539 - 1;
		} else {
			throw new IllegalArgumentException("Vaga fora de range.");
		}
	}

	public static String showVagas(int code) {
		int letraCode = code / 20;
		int posicao = code % 20;
		char letra = (char) (letraCode + 97);
		return "" + letra + String.format("%02d", posicao);
	}

	// Emitir nota
	public void emitirNota() {
		if (getPedido() != null) {
			System.out.printf("Veículo retirado da garagem, placa: %s, modelo: %s, data: %s\n",
					getPedido().getCarro().getPlaca(), getPedido().getCarro().getModelo(),
					LocalDateTime.now().toString());
		}
	}

	public void printarVagasLista() {
		// catch numberformat exception
		try {
			System.out.println("1 - Vagas Flexíveis/n2 - Vagas Agendadas\nPressione Enter para voltar");
			String tipoVaga = sc.nextLine().trim();
			int opcao = Integer.parseInt(tipoVaga);
			ArrayList<PedidoEstacionar> vagasDisponiveis;
			if (opcao == 1) {
				vagasDisponiveis = getFiliais().getVagasFlex();
				int contador = 0;
				for (PedidoEstacionar vaga : vagasDisponiveis) {
					System.out.println(vaga);
					if (++contador % 10 == 0) {
						System.out.println();
					}
				}
			} else if (opcao == 2) {
				vagasDisponiveis = getFiliais().getVagasAgendadas();
				int contador = 0;
				for (PedidoEstacionar vaga : vagasDisponiveis) {
					System.out.println(vaga);
					if (++contador % 10 == 0) {
						System.out.println();
					}
				}
			} else {
				System.out.println("Estacionamento não identificado.");
			}
		} catch (NumberFormatException e) {
			System.out.println("O valor passado não é um número.");
			sc.nextLine();
		} catch (IllegalArgumentException e) {
			System.out.println("Argumento inesperado. Pressione enter para voltar");
			sc.nextLine();
		}
	}

	// Checar prazo
	public Long checarPrazo(PedidoEstacionar pedido, int opcao) {
		if (pedido.getFinish() == null) {
			return null;
		}
		Duration duracao = Duration.between(pedido.getStart(), pedido.getFinish());
		switch (opcao) {
		case 1:
			return duracao.toMinutes();
		case 2:
			return duracao.toHours();
		case 3:
			return duracao.toDays();
		default:
			throw new IllegalArgumentException("Opção de formatação de tempo indefinida");
		}
	}

	// Checar vencimentos
	public void checarVencimentos() {
		Long prazo = checarPrazo(getPedido(), 3);
		if (prazo == null)
			return;

		if (prazo > 30 && getPedido().getVagaCarro() == VagaCarro.Vaga_Agendada) {
			finalizarPedido(getPedido());
			removerCarro(getPedido());
		} else if (prazo > 3 && getPedido().getVagaCarro() == VagaCarro.Vaga_Flexivel) {
			finalizarPedido(getPedido());
			removerCarro(getPedido());
		}
	}

	// Atualizar sistema
	public void atualizarSistema() {
		checarVencimentos();
		// Implementar lógica adicional de atualização do sistema conforme necessário
	}

	// Finalizar pedido
	public void finalizarPedido(PedidoEstacionar pedido) {
		pedido.setFinish(LocalDateTime.now());
		// Validar
	}

	// Editar vagas
	public void editarVagas(Filiais filial) {
		String vaga;
		String placa;
		boolean fimOp = false;

		do {
			try {
				int opcao = UI.getRequest(4);
				switch (opcao) {
				case 1:
					System.out.println("Digite a vaga a ser preenchida: ");
					vaga = sc.next();

					// Valida o formato da vaga
					if (!ValidarDados.validarVaga(vaga)) {
						throw new OperacaoError("Erro de operação, a vaga deve ter um formato letra e 2 dígitos.");
					}

					// Converte a vaga para código
					int code = lerVagas(vaga);

					System.out.println(
							"1 - Informe a placa do veículo para ocupar\n2 - Pressione enter para desocupar\n3 - Voltar");
					int opcao2 = sc.nextInt();
					sc.nextLine(); // Limpar o buffer do scanner

					switch (opcao2) {
					case 1:
						// Ocupação de vaga
						System.out.println("Digite a placa do veículo: ");
						placa = sc.next().trim();

						// Valida a placa do veículo
						if (!ValidarDados.validarPlaca(placa)) {
							throw new OperacaoError("Erro de operação, a placa deve ter 7 dígitos.");
						}

						PedidoEstacionar pedidoOcupacao = filial.searchPlate(placa);
						if (pedidoOcupacao != null) {
							if (code < filial.getTotalVagas()[0].size()) {
								// Vaga agendada
								if (filial.getTotalVagas()[0].get(code) == null) {
									filial.getTotalVagas()[0].set(code, pedidoOcupacao);
									System.out.println("Vaga agendada ocupada com sucesso.");
								} else {
									System.out.println("A vaga agendada já está ocupada.");
								}
							} else {
								int vagaFlex = code - filial.getTotalVagas()[0].size();
								if (filial.getTotalVagas()[1].get(vagaFlex) == null) {
									// Vaga flexível
									filial.getTotalVagas()[1].set(vagaFlex, pedidoOcupacao);
									System.out.println("Vaga flexível ocupada com sucesso.");
								} else {
									System.out.println("A vaga flexível já está ocupada.");
								}
							}
						} else {
							throw new IllegalArgumentException("Pedido não encontrado com a placa: " + placa);
						}
						break;

					case 2:
						// Desocupação de vaga
						if (code < filial.getTotalVagas()[0].size()) {
							// Vaga agendada
							if (filial.getTotalVagas()[0].get(code) != null) {
								filial.getTotalVagas()[0].set(code, null);
								System.out.println("Vaga agendada desocupada com sucesso.");
							} else {
								System.out.println("A vaga agendada já está desocupada.");
							}
						} else {
							int vagaFlex = code - filial.getTotalVagas()[0].size();
							if (filial.getTotalVagas()[1].get(vagaFlex) != null) {
								filial.getTotalVagas()[1].set(vagaFlex, null);
								System.out.println("Vaga flexível desocupada com sucesso.");
							} else {
								System.out.println("A vaga flexível já está desocupada.");
							}
						}
						break;

					case 3:
						System.out.println("Voltando...");
						break;

					default:
						System.out.println("Opção inválida.");
						break;
					}
					break;

				case 2:
					System.out.println("Digite a placa do veículo para localizarmos no sistema");
					placa = sc.next().trim();

					// Localiza o pedido com base na placa
					PedidoEstacionar pedidoPlaca = filial.searchPlate(placa);

					if (pedidoPlaca != null) {
						System.out.println("O pedido está na vaga: " + pedidoPlaca.getVaga());
						System.out.println("1 - Realocar pedido para outra vaga\n2 - Voltar");

						int opcaoRealocar = sc.nextInt();
						sc.nextLine(); // Limpar o buffer do scanner

						switch (opcaoRealocar) {
						case 1:
							// Chama o método para substituir a vaga
							filial.substituirVaga(filial, pedidoPlaca);
							break;
						case 2:
							System.out.println("Voltando...");
							break;
						default:
							System.out.println("Opção inválida.");
							break;
						}
					} else {
						System.out.println("Pedido não encontrado com a placa: " + placa);
					}
					break;

				case 3:
					System.out.println("Voltando...");
					Thread.sleep(3000);
					break;

				default:
					System.out.println("Opção inválida.");
					break;
				}
				fimOp = true;
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Tente novamente.");
				sc.next(); // Limpar o buffer do scanner
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Erro ao pausar o sistema.");
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
		} while (!fimOp);
	}

	// Getters e Setters
	public int getTotalCarros() {
		return totalCarros;
	}

	public void setTotalCarros(int totalCarros) {
		Garagem.totalCarros = totalCarros;
	}

	public Filiais getFiliais() {
		return filiais;
	}

	public PedidoEstacionar getPedido() {
		return pedido;
	}

	public void setPedido(PedidoEstacionar pedido) {
		this.pedido = pedido;
	}
}
