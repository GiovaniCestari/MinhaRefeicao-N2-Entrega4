package minharefeicao.excecao;

/**
 * Fluxo Alternativo FA2: o Assinante tentou selecionar mais refeicoes
 * do que o plano contratado permite.
 */
public class LimiteRefeicoesExcedidoException extends Exception {
    private final int limitePlano;
    private final int totalTentado;

    public LimiteRefeicoesExcedidoException(int limitePlano, int totalTentado) {
        super("Selecao de " + totalTentado + " refeicoes excede o limite de " + limitePlano + " do plano.");
        this.limitePlano = limitePlano;
        this.totalTentado = totalTentado;
    }

    public int getLimitePlano() { return limitePlano; }
    public int getTotalTentado() { return totalTentado; }
}
