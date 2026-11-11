package minharefeicao.fronteira;

import minharefeicao.entidade.Pagamento;

public class OperadoraCartao {
    private String nomeOperadora;

    public OperadoraCartao(String nomeOperadora) {
        this.nomeOperadora = nomeOperadora;
    }

    public boolean autorizarTransacao(Pagamento pg) {
        String numCartao = pg.getCartaoCredito().getNumero().replaceAll("\\D", "");
        boolean autorizado = !numCartao.endsWith("0000");
        System.out.println("  [Seq 48: opCartao:OperadoraCartao.autorizarTransacao] Consultando " + nomeOperadora
                + " para R$ " + String.format("%.2f", pg.getValor()) + " -> autorizado = " + autorizado);
        return autorizado;
    }
}
