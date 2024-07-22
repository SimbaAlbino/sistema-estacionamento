package entidades;

public class Funcionario {
	private String nome;
	private String email;
	private String senha;
	
	private String caminhoFuncionariosFile = "C:\\Users\\Pedro\\Desktop\\Study\\sistema-estacionamento\\files\\cadastrosFuncionarios.txt"; // caminho a ser criado
	
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
	
	public String getCaminhoFuncionariosFile() {
		return caminhoFuncionariosFile;
	}
	
	
	@Override
	public String toString() {
		return "Funcionario: nome=" + nome + ", email=" + email;
	}
}
