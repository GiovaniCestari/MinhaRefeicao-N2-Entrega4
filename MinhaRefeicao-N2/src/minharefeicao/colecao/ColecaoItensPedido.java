package minharefeicao.colecao;

import minharefeicao.entidade.ItemPedido;
import java.util.ArrayList;
import java.util.List;

public class ColecaoItensPedido {
    private List<ItemPedido> itens;

    public ColecaoItensPedido() {
        this.itens = new ArrayList<>();
    }

    public boolean add(ItemPedido ip) {
        System.out.println("  [Seq 35: :ItemPedido(ColecaoItensPedido).add] Item adicionado a colecao de itens do pedido.");
        return this.itens.add(ip);
    }

    public boolean remover(ItemPedido ip) {
        System.out.println("  [Seq FA2: :ItemPedido(ColecaoItensPedido).remover] Item removido: a selecao ultrapassava o limite do plano.");
        return this.itens.remove(ip);
    }

    public int calcularTotalPorcoes() {
        int total = 0;
        for (ItemPedido item : itens) {
            total += item.getSubtotalPorcoes();
        }
        return total;
    }

    public List<ItemPedido> getItens() { return itens; }
}
