package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {
	
	static Scanner input = new Scanner(System.in);

	public static int getRequest(int vetorAtual) {
		List<String> opcoes = null;
		switch (vetorAtual) {
		case 1:
			opcoes = Arrays.asList("Cliente", "Funcionario", "Finalizar");
			break;
		case 2:
			opcoes = Arrays.asList("Mostrar Vagas", "Agendar", "Pagar", "Voltar");
			break;
		case 3:
			opcoes = Arrays.asList("Boa Garagem", "Garagem Cia", "Pare Bem", "Voltar");
			break;
		default:
			throw new IllegalArgumentException("Opção inválida, tente novamente");
		}
		
		boolean entrada = false;
		int request = 4;
		short contador;
		// Exibe opções de solicitação
		
		do {
			contador = 1;
			for (String opcao : opcoes) {
				System.out.printf("%d - %s\n", contador++, opcao);
			}
			try {
				 // Insere a solicitação de usuário
					System.out.print("\n-> ");
					String digito = input.nextLine().trim();
					if (digito.isEmpty()) {
						throw new IllegalArgumentException("Valor nulo lançado");
					}
					request = Integer.parseInt(digito);
					clearScreen();
					if (request < 1 || request > opcoes.size()) {
						System.out.println("Valor fora dos parâmetros, digite entre 1 e " + opcoes.size());
					} else {
						entrada = true;
					}
			} catch (InputMismatchException e) {
				System.err.printf("Entrada inválida, %s, digite enter para tentar novamente.\n", e.getMessage());
				input.nextLine();
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida, por favor insira um número. Digite enter para tentar novamente.");
				input.nextLine();
			}
		} while (!entrada || (request < 1) || (request > opcoes.size()));
		clearScreen();
		return request; // Retorna o valor enum da opção
	}
	
	public static void clearScreen() {
		for (int i = 0; i < 50; ++i) System.out.println();
	}
}
