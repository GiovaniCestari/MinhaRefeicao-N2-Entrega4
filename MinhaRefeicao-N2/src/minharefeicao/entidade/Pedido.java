package minharefeicao.entidade;

import minharefeicao.colecao.ColecaoItensPedido;
import minharefeicao.enums.StatusPedido;
import java.time.LocalDate;

public class Pedido {
    private Long id;
    private LocalDate dataElaboracao;
    private StatusPedido status;
    private ColecaoItensPedido colecaoItens;

    private Pedido() {
        this.id = 1001L + (System.currentTimeMillis() % 8999);
        this.dataElaboracao = LocalDate.now();
        this.status = StatusPedido.EM_ELABORACAO;
        this.colecaoItens = new ColecaoItensPedido();
    }

    public static Pedido criarPedidoInicial() {
        System.out.println("  [Seq 18: <<create>> pe:Pedido.criarPedidoInicial] Pedido inicial criado com status EM_ELABORACAO");
        return new Pedido();
    }

    public int adicionarItem(Refeicao r, int qtde, int limitePlano) {
        System.out.println("  [Seq 28: pe:Pedido.adicionarItem] Adicionando refeicao ao pedido...");
        ItemPedido ip = ItemPedido.criar(r, qtde);
        this.colecaoItens.add(ip);
        return validarLimiteRefeicoes(limitePlano);
    }

    public int validarLimiteRefeicoes(int limitePlano) {
        int totalSelecionado = this.colecaoItens.calcularTotalPorcoes();
        int saldoRestante = Math.max(0, limitePlano - totalSelecionado);
        System.out.println("  [Seq 33: pe:Pedido.validarLimiteRefeicoes] Total no pedido: " + totalSelecionado + "/" + limitePlano + " (Saldo restante: " + saldoRestante + ")");
        return saldoRestante;
    }

    public void aprovarPedido() {
        this.status = StatusPedido.APROVADO;
        System.out.println("  [Seq 51: pe:Pedido.aprovarPedido] Status do Pedido atualizado para: " + this.status);
    }

    public Long getId() { return id; }
    public StatusPedido getStatus() { return status; }
    public ColecaoItensPedido getColecaoItens() { return colecaoItens; }
}
