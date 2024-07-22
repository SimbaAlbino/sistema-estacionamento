package entidades;

public class ValidarDados {
	public static boolean validarIdVaga(Integer id) {
		String idProduto = "" + id;
		return idProduto != null && idProduto.matches("\\d{3}");
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
}	
