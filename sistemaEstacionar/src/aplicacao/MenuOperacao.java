package aplicacao;

public enum MenuOperacao {
	Mostrar_Vagas(1),
	Agendar(2),
	Pagar(3),
	Voltar(4);
	
    private final int value; // Opção atual de menu

    // Construtor
    private MenuOperacao(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    
}