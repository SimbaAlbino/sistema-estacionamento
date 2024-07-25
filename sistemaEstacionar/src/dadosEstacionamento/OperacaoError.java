package dadosEstacionamento;

public class OperacaoError extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public OperacaoError(String msg) {
		super(msg);
	}
}
