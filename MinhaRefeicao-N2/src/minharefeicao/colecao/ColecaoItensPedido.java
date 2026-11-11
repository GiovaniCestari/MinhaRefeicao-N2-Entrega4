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
        System.out.println("  [Seq 32: :ItemPedido(ColecaoItensPedido).add] Item adicionado a colecao de itens do pedido.");
        return this.itens.add(ip);
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
