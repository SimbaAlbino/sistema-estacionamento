package estacionamentos;

import java.util.ArrayList;

import entidades.PedidoEstacionar;
import sistemaInterno.Taxas;

public class BoaGaragem extends Filiais {
	
	private int totalVagasAge = 30;
	private int totalVagasFlex = 80;
	private ArrayList<PedidoEstacionar> vagasLivresAgendada;
	private ArrayList<PedidoEstacionar> vagasLivresFlex;
	
}
