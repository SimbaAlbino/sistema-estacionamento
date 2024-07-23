package entidades;

import java.util.ArrayList;
import java.util.Scanner;

import aplicacao.UI;
import dadosEstacionamento.ModelagemFile;

public class Funcionario {
	private String nome;
	private String email;
	private String senha;
	
	private static String caminhoFuncionariosFile = "C:\\Users\\Pedro\\Desktop\\Study\\sistema-estacionamento\\files\\cadastrosFuncionarios.txt"; // caminho a ser criado
	
	public Funcionario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
	
	public Funcionario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	public Funcionario() {
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public static String getCaminhoFuncionariosFile() {
		return caminhoFuncionariosFile;
	}


	public static void setCaminhoFuncionariosFile(String caminhoFuncionariosFile) {
		Funcionario.caminhoFuncionariosFile = caminhoFuncionariosFile;
	}


	public static String[] loginUser() {
		Scanner sc = null;
		String[] dados = new String[2];
		short contador = 3;
		try {
			sc = new Scanner(System.in);
			System.out.println("- Login -:");
			do {
				System.out.print("E-mail: ");
				dados[0] = sc.next();
				System.out.print("Senha: ");
				dados[1] = sc.next();
				sc.nextLine();
				if (confirmarUser(dados)) {
					break;
				} else {
					System.out.println("E-mail/Senha errados, tente novamente!");
					contador--;
				}
			} while (contador > 0);
			if (dados[0] == null || dados[1] == null) {
				throw new IllegalArgumentException(
						"Seus dados não correnspondem ao sistema. Tente novamente mais tarde.");
			} else {
				UI.clearScreen();
				System.out.println("Bem-vindo ao Sistema Alfândega.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Erro por um argumento ilegal: " + e.getMessage());
		} 
		return dados;
	}

	public static boolean confirmarUser(String[] dadosEntrada) {
		ArrayList<Funcionario> funcionarios = listarUsuarios(getCaminhoFuncionariosFile());
		if (funcionarios == null) {
			System.out.println("Não existe este cadastro no registro.");
		} else {
			Funcionario funcionario = new Funcionario(dadosEntrada[0], dadosEntrada[1]);
			for (Funcionario pessoa : funcionarios) {
				if (pessoa.equalsByEmailAndSenha(funcionario.getEmail(), funcionario.getSenha())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static ArrayList<Funcionario> listarUsuarios(String caminho) {
		ArrayList<Funcionario> listaPessoas = null;
		try {
			listaPessoas = ModelagemFile.desserializar(caminho);
		} catch (NullPointerException e) {
			System.out.printf("Não há funcionários na lista.");
		}
		return listaPessoas;
	}
	
	public void operacoes() {
		
	}
	
	public boolean equalsByEmailAndSenha(String email, String senha) {
		return this.email.equals(email) && this.senha.equals(senha);
	}
	
	@Override
	public String toString() {
		return "Funcionario: nome=" + nome + ", email=" + email;
	}
}
