package minharefeicao.entidade;

public class ItemPedido {
    private Refeicao refeicao;
    private int quantidade;
    private int subtotalPorcoes;

    private ItemPedido(Refeicao refeicao, int quantidade) {
        this.refeicao = refeicao;
        this.quantidade = quantidade;
    }

    public static ItemPedido criar(Refeicao r, int qtde) {
        System.out.println("  [Seq 32: <<create>> ip:ItemPedido.criar] Instanciando ItemPedido para '" + r.getNome() + "' (qtde: " + qtde + ")");
        ItemPedido item = new ItemPedido(r, qtde);
        item.calcularSubtotalPorcoes();
        return item;
    }

    public int calcularSubtotalPorcoes() {
        this.subtotalPorcoes = this.quantidade;
        System.out.println("  [Seq 33: ip:ItemPedido.calcularSubtotalPorcoes] Subtotal calculado: " + subtotalPorcoes + " porcao(oes)");
        return this.subtotalPorcoes;
    }

    public Refeicao getRefeicao() { return refeicao; }
    public int getQuantidade() { return quantidade; }
    public int getSubtotalPorcoes() { return subtotalPorcoes; }
}
