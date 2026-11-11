package minharefeicao.entidade;

import minharefeicao.enums.StatusPagamento;
import java.time.LocalDateTime;

public class Pagamento {
    private Long id;
    private double valor;
    private LocalDateTime dataHoraTransacao;
    private StatusPagamento status;
    private CartaoCredito cartaoCredito;

    private Pagamento(double valorTotal, CartaoCredito cc) {
        this.id = 5001L + (System.currentTimeMillis() % 4999);
        this.valor = valorTotal;
        this.dataHoraTransacao = LocalDateTime.now();
        this.status = StatusPagamento.PENDENTE;
        this.cartaoCredito = cc;
    }

    public static Pagamento criar(double valorTotal, CartaoCredito cc) {
        System.out.println("  [Seq 47: <<create>> pg:Pagamento.criar] Pagamento criado no valor de R$ " + String.format("%.2f", valorTotal) + " (Status: PENDENTE)");
        return new Pagamento(valorTotal, cc);
    }

    public void confirmarAutorizacao() {
        this.status = StatusPagamento.AUTORIZADO;
        System.out.println("  [Seq 50: pg:Pagamento.confirmarAutorizacao] Status do Pagamento atualizado para: " + this.status);
    }

    public void registrarRecusa() {
        this.status = StatusPagamento.NAO_AUTORIZADO;
        System.out.println("  [Seq A2: pg:Pagamento.registrarRecusa] Status do Pagamento atualizado para: " + this.status);
    }

    public Long getId() { return id; }
    public double getValor() { return valor; }
    public StatusPagamento getStatus() { return status; }
    public CartaoCredito getCartaoCredito() { return cartaoCredito; }
}
