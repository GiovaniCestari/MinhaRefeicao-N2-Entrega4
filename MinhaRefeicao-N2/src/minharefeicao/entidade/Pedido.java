package minharefeicao.entidade;

import minharefeicao.colecao.ColecaoItensPedido;
import minharefeicao.enums.StatusPedido;
import minharefeicao.excecao.LimiteRefeicoesExcedidoException;
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
        System.out.println("  [Seq 19: <<create>> pe:Pedido.criarPedidoInicial] Pedido inicial criado com status EM_ELABORACAO");
        return new Pedido();
    }

    public int adicionarItem(Refeicao r, int qtde, int limitePlano) throws LimiteRefeicoesExcedidoException {
        System.out.println("  [Seq 31: pe:Pedido.adicionarItem] Adicionando refeicao ao pedido...");
        ItemPedido ip = ItemPedido.criar(r, qtde);
        this.colecaoItens.add(ip);
        try {
            return validarLimiteRefeicoes(limitePlano);
        } catch (LimiteRefeicoesExcedidoException e) {
            // FA2: desfaz a inclusao que ultrapassou o limite e repassa o erro ao controller
            this.colecaoItens.remover(ip);
            throw e;
        }
    }

    public int validarLimiteRefeicoes(int limitePlano) throws LimiteRefeicoesExcedidoException {
        int totalSelecionado = this.colecaoItens.calcularTotalPorcoes();
        if (totalSelecionado > limitePlano) {
            System.out.println("  [Seq 36: pe:Pedido.validarLimiteRefeicoes] Total no pedido: " + totalSelecionado + "/" + limitePlano + " -> LIMITE DO PLANO EXCEDIDO");
            throw new LimiteRefeicoesExcedidoException(limitePlano, totalSelecionado);
        }
        int saldoRestante = limitePlano - totalSelecionado;
        System.out.println("  [Seq 36: pe:Pedido.validarLimiteRefeicoes] Total no pedido: " + totalSelecionado + "/" + limitePlano + " (Saldo restante: " + saldoRestante + ")");
        return saldoRestante;
    }

    public void aprovarPedido() {
        this.status = StatusPedido.APROVADO;
        System.out.println("  [Seq 58: pe:Pedido.aprovarPedido] Status do Pedido atualizado para: " + this.status);
    }

    public Long getId() { return id; }
    public StatusPedido getStatus() { return status; }
    public ColecaoItensPedido getColecaoItens() { return colecaoItens; }
}
