package minharefeicao.entidade;

import minharefeicao.enums.StatusAssinatura;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Assinatura {
    private String protocolo;
    private LocalDate dataCriacao;
    private LocalDate previsaoPrimeiraEntrega;
    private double valorTotal;
    private StatusAssinatura status;
    private Assinante assinante;
    private Plano plano;
    private Pedido pedidoInicial;
    private Endereco endereco;
    private Pagamento pagamento;

    private Assinatura(Assinante a, Plano pl) {
        this.assinante = a;
        this.plano = pl;
        this.dataCriacao = LocalDate.now();
        this.status = StatusAssinatura.AGUARDANDO_PAGAMENTO;
        this.pedidoInicial = Pedido.criarPedidoInicial();
    }

    public static Assinatura criar(Assinante a, Plano pl) {
        System.out.println("  [Seq 17: <<create>> as:Assinatura.criar] Criando Assinatura para o celular " + a.getCelular());
        return new Assinatura(a, pl);
    }

    public void vincularEndereco(Endereco en) {
        this.endereco = en;
        System.out.println("  [Seq 39: as:Assinatura.vincularEndereco] Endereco vinculado: " + en.getResumoFormatado());
        calcularValorTotal();
        atualizarStatus(StatusAssinatura.AGUARDANDO_PAGAMENTO);
    }

    public double calcularValorTotal() {
        this.valorTotal = this.plano.getValor();
        System.out.println("  [Seq 40: as:Assinatura.calcularValorTotal] Valor total apurado: R$ " + String.format("%.2f", this.valorTotal));
        return this.valorTotal;
    }

    public void atualizarStatus(StatusAssinatura novoStatus) {
        this.status = novoStatus;
        System.out.println("  [Seq 41: as:Assinatura.atualizarStatus] Status da Assinatura: " + this.status);
    }

    public String ativarAssinatura() {
        this.status = StatusAssinatura.ATIVA;
        gerarProtocoloEPrevisaoEntrega();
        System.out.println("  [Seq 52: as:Assinatura.ativarAssinatura] Assinatura ATIVA! Protocolo: " + this.protocolo);
        return this.protocolo;
    }

    public void gerarProtocoloEPrevisaoEntrega() {
        this.protocolo = "MR-2026-" + (1000 + (int)(Math.random() * 8999));
        this.previsaoPrimeiraEntrega = LocalDate.now().plusDays(2);
        System.out.println("  [Seq 53: as:Assinatura.gerarProtocoloEPrevisaoEntrega] Protocolo " + this.protocolo
                + " | Previsao 1a Entrega: " + getPrevisaoFormatada());
    }

    public void setPagamento(Pagamento pagamento) { this.pagamento = pagamento; }
    public String getProtocolo() { return protocolo; }
    public String getPrevisaoFormatada() {
        return previsaoPrimeiraEntrega != null ? previsaoPrimeiraEntrega.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A";
    }
    public double getValorTotal() { return valorTotal; }
    public StatusAssinatura getStatus() { return status; }
    public Assinante getAssinante() { return assinante; }
    public Plano getPlano() { return plano; }
    public Pedido getPedidoInicial() { return pedidoInicial; }
    public Endereco getEndereco() { return endereco; }
    public Pagamento getPagamento() { return pagamento; }
}
