package aplicacao;

import java.util.Scanner;

import dadosEstacionamento.Garagem;
import dadosEstacionamento.VagaCarro;
import entidades.Funcionario;
import entidades.PedidoEstacionar;
import entidades.User;
import estacionamentos.BoaGaragem;
import estacionamentos.Filiais;
import estacionamentos.GaragemCia;
import estacionamentos.PareBem;

public class Programa {

    final static MenuOperacao[] choices = MenuOperacao.values();
    final static User[] choicesUser = User.values();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User conta;
        Filiais filial = null;

        // Selecionar a conta do usuário
        conta = choicesUser[UI.getRequest(1) - 1];
        
        while(conta != User.FINALIZAR) {
        	
        	// Selecionar o estacionamento
        	int opcao = UI.getRequest(3);
        	switch (opcao) {
        	case 1:
        		filial = new BoaGaragem();
        		break;
        	case 2:
        		filial = new GaragemCia();
        		break;
        	case 3:
        		filial = new PareBem();
        		break;
        	case 4:
        		System.out.println("Saindo do programa...");
        		try {
        			Thread.sleep(3000);
        		} catch (InterruptedException e) {
        			System.out.println("Erro no sleep");
        		}
        		UI.clearScreen();
        		sc.close(); // Fechar o Scanner antes de sair
        		System.exit(0); // Termina o programa
        		break;
        	default:
        		System.out.println("Opção inválida.");
        		sc.close(); // Fechar o Scanner antes de sair
        		System.exit(1); // Termina o programa com erro
        		break;
        	}
        	if (conta == User.CLIENTE) {
        		MenuOperacao operacao = choices[UI.getRequest(2) - 1];
        		
        		while (operacao != MenuOperacao.Voltar) {
        			switch (operacao) {
        			case Mostrar_Vagas:
        				Garagem.printarVagasLista(filial); // Mostra vagas flexíveis
        				break;
        			case Agendar:
        				System.out.printf("%nSistema de agendamento:%n");
        				System.out.println("Digite a placa do veículo:");
        				String placa = sc.next().trim();
        				PedidoEstacionar pedido = filial.searchPlate(placa);
        				System.out.println("Digite a vaga para agendar:");
        				String vaga = sc.next().trim();
        				int code = Garagem.lerVagas(vaga);
        				// Atualize o pedido com a nova vaga e defina o status apropriado
        				pedido.setVaga(vaga);
        				pedido.setVagaCarro(VagaCarro.Vaga_Agendada);
        				filial.ocuparVaga(code, pedido);
        				System.out.println("Veículo agendado com sucesso.");
        				break;
        				
        			case Pagar:
        				System.out.printf("%nPagar Dívidas:%n");
        				// Adicione a lógica de pagamento de dívidas aqui
        				break;
        				
        			default:
        				System.out.println("Operação não reconhecida.");
        				break;
        			}
        			
        			UI.clearScreen(); // Limpa a tela (presumindo que esse método existe e funciona)
        			operacao = choices[UI.getRequest(2) - 1]; // Obtém a solicitação do usuário
        		}
        	} else if (conta == User.FUNCIONARIO) {
        		if (Funcionario.confirmarUser(Funcionario.loginUser())) {
        			Funcionario funcionario = new Funcionario();
        			funcionario.operacoes();
        		} else {
        			System.out.println("Credenciais inválidas. Tente novamente.");
        		}
        	} else {
        		System.out.println("Tipo de usuário não reconhecido.");
        		break;
        	}
        	
        	// Solicitar a próxima escolha de usuário
        	conta = choicesUser[UI.getRequest(1) - 1];
        }
        
        sc.close(); // Fechar o Scanner quando terminar
    }
}
