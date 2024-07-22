package estacionamentos;

import java.util.ArrayList;

import entidades.PedidoEstacionar;
import sistemaInterno.Taxas;

public class GaragemCia extends Filiais {
	
	private int totalVagasAge = 20;
	private int totalVagasFlex = 60;
	private ArrayList<PedidoEstacionar> vagasLivresAgendada;
	private ArrayList<PedidoEstacionar> vagasLivresFlex;
	
}
