package estacionamentos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import dadosEstacionamento.Garagem;
import dadosEstacionamento.VagaCarro;
import entidades.PedidoEstacionar;

public abstract class Filiais {

    private PedidoEstacionar pedido;
    private static Scanner sc = new Scanner(System.in);

    private String id; // Identificador único da filial
    private List<List<PedidoEstacionar>> totalVagas;
    private List<PedidoEstacionar> vagasAgendadas;
    private List<PedidoEstacionar> vagasFlex;

    // Caminhos dos arquivos
    private static final String CAMINHO_BASE = "C:\\Users\\pedro\\Desktop\\Study\\sistema-estacionamento\\files\\";
    private String caminhoVagasAgendadas = CAMINHO_BASE + "vagas_agendadas.txt";
    private String caminhoVagasFlexiveis = CAMINHO_BASE + "vagas_flexiveis.txt";

    // Construtores
    public Filiais(PedidoEstacionar pedido) {
        this.pedido = pedido;
    }

    public Filiais() {
    }

    // Getters e Setters
    public PedidoEstacionar getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEstacionar pedido) {
        this.pedido = pedido;
    }

    public String getCaminhoVagasAgendadas() {
        return caminhoVagasAgendadas;
    }

    public String getCaminhoVagasFlexiveis() {
        return caminhoVagasFlexiveis;
    }

    public abstract String getFileEmpresa();

    public abstract ArrayList<PedidoEstacionar> getVagasAgendadas();

    public abstract ArrayList<PedidoEstacionar> getVagasFlex();

    public List<PedidoEstacionar> getVagasAgendadasList() {
        return vagasAgendadas;
    }

    public List<PedidoEstacionar> getVagasFlexList() {
        return vagasFlex;
    }

    public ArrayList<PedidoEstacionar>[] getTotalVagas() {
        @SuppressWarnings("unchecked")
        ArrayList<PedidoEstacionar>[] vagasTotais = new ArrayList[] { new ArrayList<>(getVagasAgendadas()), new ArrayList<>(getVagasFlex()) };
        return vagasTotais;
    }

    public PedidoEstacionar searchPlate(String placa) {
        for (ArrayList<PedidoEstacionar> pedidos : getTotalVagas()) {
            Optional<PedidoEstacionar> pedidoEncontrado = pedidos.stream()
                    .filter(pedido -> placa.equals(pedido.getCarro().getPlaca())).findFirst();

            if (pedidoEncontrado.isPresent()) {
                return pedidoEncontrado.get();
            }
        }
        throw new RuntimeException("Nenhum pedido encontrado com a placa: " + placa);
    }

    public void mostrarStatusLista(VagaCarro tipoVaga) {
        ArrayList<PedidoEstacionar> vagasTotais;
        if (tipoVaga == VagaCarro.Vaga_Flexivel) {
            vagasTotais = getTotalVagas()[1];
            System.out.println("Vagas livres disponíveis: \n");
        } else if (tipoVaga == VagaCarro.Vaga_Agendada) {
            vagasTotais = getTotalVagas()[0];
            System.out.println("Vagas por agendamento disponíveis: \n");
        } else {
            System.out.println("Tipo de vaga inválido.");
            return;
        }

        for (int i = 0; i < vagasTotais.size(); i++) {
            if (vagasTotais.get(i) == null) {
                System.out.println(Garagem.showVagas(i) + " - Livre");
            } else {
                System.out.println(Garagem.showVagas(i) + " - Ocupada por: " + vagasTotais.get(i).getCarro().getPlaca());
            }
        }
    }

    public abstract boolean ocuparVaga(int vaga, PedidoEstacionar pedido);

    public abstract boolean desocuparVaga(int vaga);

    public void substituirVaga(Filiais filial, PedidoEstacionar pedidoAntigo) {
        System.out.println("Vagas disponíveis:");
        Garagem garagem = new Garagem(filial);
        garagem.printarVagasLista();

        System.out.println("Digite o código da nova vaga (formato letra e 2 dígitos): ");
        String novaVagaStr = sc.next();
        int novaVaga = Garagem.lerVagas(novaVagaStr);

        // Verificar se a nova vaga é válida e está disponível
        if (novaVaga < filial.getTotalVagas()[0].size() && filial.getVagasAgendadas().get(novaVaga) != null) {
            System.out.println("A nova vaga selecionada já está ocupada.");
            return;
        } else if (novaVaga >= filial.getTotalVagas()[0].size() && filial.getVagasFlex().get(novaVaga - filial.getTotalVagas()[0].size()) != null) {
            System.out.println("A nova vaga selecionada já está ocupada.");
            return;
        }

        // Substituir a vaga antiga
        boolean vagaSubstituida = false;
        for (ArrayList<PedidoEstacionar> vagas : filial.getTotalVagas()) {
            for (int i = 0; i < vagas.size(); i++) {
                if (vagas.get(i) != null && vagas.get(i).equals(pedidoAntigo)) {
                    // Desocupar a vaga antiga
                    vagas.set(i, null);

                    // Ocupação da nova vaga
                    if (filial.ocuparVaga(novaVaga, pedidoAntigo)) {
                        vagaSubstituida = true;
                        System.out.println("Vaga substituída com sucesso.");
                    } else {
                        System.out.println("Falha ao ocupar a nova vaga.");
                    }
                    break;
                }
            }
            if (vagaSubstituida)
                break;
        }

        if (!vagaSubstituida) {
            System.out.println("Pedido antigo não encontrado na garagem.");
        }
    }
}
