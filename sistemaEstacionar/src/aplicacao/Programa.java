package aplicacao;

import entidades.Funcionario;
import entidades.User;

public class Programa {

	final static MenuOperacao[] choices = MenuOperacao.values();
	final static User[] choicesUser = User.values();

	public static void main(String[] args) {
		User conta = choicesUser[UI.getRequest(1) - 1];

		while (conta != User.FINALIZAR) {
			if (conta == User.CLIENTE) {
				MenuOperacao operacao = choices[UI.getRequest(2) - 1];
				// pedir para passar a placa do carro e o cpf

				while (operacao != MenuOperacao.Voltar) {
					switch (operacao) {
					case Mostrar_Vagas:
						System.out.printf("%nMostrando vagas disponíveis:%n");
						break;
					case Agendar:
						System.out.printf("%nSistema de agendamento:%n");

						break;
					case Pagar:
						System.out.printf("%nPagar Dívidas:%n");
						break;
					default:
						break;
					}

					UI.clearScreen();
					operacao = choices[UI.getRequest(2) - 1]; // Obtém a solicitação do usuário
					// operacao vai carregar a opcao de usuario
				}
			} else if (conta == User.FUNCIONARIO) {
				if (Funcionario.confirmarUser(Funcionario.loginUser())) {
					Funcionario funcionario = new Funcionario();
					funcionario.operacoes();
					// atualizar console depois das operações de usuario dentro do método
				}
			} else {
				break;
			}
			conta = choicesUser[UI.getRequest(1) - 1];
		}
	}
}
