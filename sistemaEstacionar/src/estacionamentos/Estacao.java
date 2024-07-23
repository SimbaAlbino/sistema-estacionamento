package estacionamentos;

import java.util.ArrayList;

public abstract class Estacao {
	protected ArrayList<String> vagaAgendada;
	protected ArrayList<String> vagaFlex;
	protected int numeroVagasAge;
	protected int numeroVagasFlex;
	protected double valorBase;
	protected double taxaFixaAdd;
	protected double valorDaDiaria;

	public Estacao() {
		this.vagaAgendada = new ArrayList<>();
		this.vagaFlex = new ArrayList<>();
	}

	public abstract double calcularValorTotalAgendamento(int dias);

	public String getFileEmpresa() {
		return "Detalhes da Empresa";
	}

	public String getNotaFiscal() {
		return "Nota Fiscal";
	}
}
