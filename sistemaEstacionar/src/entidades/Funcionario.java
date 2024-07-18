package entidades;

public class Funcionario {
	private String nome;
	private String email;
	private String senha;
	
	private String caminhoFuncionariosFile; // caminho a ser criado
	
	public Funcionario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
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
	
	public static boolean validarCPF(String cpf) {
		// Verifica se o CPF não é nulo e se possui exatamente 11 dígitos numéricos
		return cpf != null && cpf.matches("\\d{11}");
	}
	
	public static boolean validarEmail(String email) {
		return email != null && email.contains("@");
	}
	
	public static boolean validarSenha(String senha) {
		return senha != null && (senha.length() > 6);
	}
	
	@Override
	public String toString() {
		return "Funcionario: nome=" + nome + ", email=" + email;
	}
}
