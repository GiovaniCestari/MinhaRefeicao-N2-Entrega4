package minharefeicao.fronteira;

import minharefeicao.controller.CtrlAssinatura;
import minharefeicao.entidade.Assinatura;
import minharefeicao.entidade.Plano;
import minharefeicao.entidade.PreferenciaAlimentar;
import minharefeicao.entidade.Refeicao;

import java.util.List;
import java.util.Map;

public class TelaAssinatura {
    private CtrlAssinatura ctrlAssinatura;

    public TelaAssinatura(CtrlAssinatura ctrlAssinatura) {
        this.ctrlAssinatura = ctrlAssinatura;
        this.ctrlAssinatura.setViewAssinatura(this);
    }

    public void solicitarAutenticacaoSMS(String celular) {
        System.out.println("\n[Seq 1: Ator -> viewAssinatura.solicitarAutenticacaoSMS] Celular: " + celular);
        ctrlAssinatura.solicitarCodigoSMS(celular);
    }

    public void confirmarAutenticacaoSMS(String celular, String codigoInformado) {
        System.out.println("\n[Seq 7: Ator -> viewAssinatura.confirmarAutenticacaoSMS] Codigo inserido: " + codigoInformado);
        ctrlAssinatura.validarCodigoSMS(celular, codigoInformado);
    }

    public void selecionarPlanoEPreferencias(String idPlano, List<PreferenciaAlimentar> listaPref) {
        System.out.println("\n[Seq 13: Ator -> viewAssinatura.selecionarPlanoEPreferencias] Plano: " + idPlano + " | Preferencias: " + listaPref);
        ctrlAssinatura.iniciarAssinatura(idPlano, listaPref);
    }

    public void incluirRefeicaoNoPedido(String idRefeicao, int qtde) {
        System.out.println("\n[Seq 24: Ator -> viewAssinatura.incluirRefeicaoNoPedido] Refeicao: " + idRefeicao + " | Quantidade: " + qtde);
        ctrlAssinatura.addRefeicao(idRefeicao, qtde);
    }

    public void registrarLocalEntrega(Map<String, String> dadosEndereco) {
        System.out.println("\n[Seq 35: Ator -> viewAssinatura.registrarLocalEntrega] CEP: " + dadosEndereco.get("cep"));
        ctrlAssinatura.definirEnderecoEntrega(dadosEndereco);
    }

    public void efetuarPagamentoAssinatura(String numero, String titular, String validade) {
        System.out.println("\n[Seq 42: Ator -> viewAssinatura.efetuarPagamentoAssinatura] Titular: " + titular + " | Final Cartao: " + numero.substring(Math.max(0, numero.length() - 4)));
        ctrlAssinatura.processarPagamento(numero, titular, validade);
    }

    public void confirmarEnvioSMS() {
        System.out.println("  [Seq 6: viewAssinatura.confirmarEnvioSMS] TELA: Codigo SMS enviado! Verifique seu celular.");
    }

    public void exibirErroSMS(String mensagem) {
        System.out.println("  [Seq A1: viewAssinatura.exibirErroSMS] TELA (ALERTA SMS): " + mensagem);
    }

    public void exibirPlanosDisponiveis() {
        System.out.println("  [Seq 12: viewAssinatura.exibirPlanosDisponiveis] TELA: Autenticacao concluida! Planos disponiveis:");
        for (Plano p : Plano.listarTodos().values()) {
            System.out.println("     -> [" + p.getId() + "] " + p.getNome() + " - R$ " + String.format("%.2f", p.getValor()));
        }
    }

    public void exibirCardapio(List<Refeicao> catalogo, int limitePlano) {
        System.out.println("  [Seq 23: viewAssinatura.exibirCardapio] TELA: Cardapio personalizado carregado (Selecione ate " + limitePlano + " refeicoes):");
        for (Refeicao r : catalogo) {
            System.out.println("     -> [" + r.getId() + "] " + r.getNome() + " | " + r.obterDescricaoCategoria());
        }
    }

    public void atualizarBarraProgresso(int saldoRestante) {
        System.out.println("  [Seq 34: viewAssinatura.atualizarBarraProgresso] TELA: Barra de progresso atualizada! Refeicoes restantes para fechar o plano: " + saldoRestante);
    }

    public void exibirResumoParaPagamento(Assinatura resumo) {
        System.out.println("  [Seq 41: viewAssinatura.exibirResumoParaPagamento] TELA DE RESUMO:");
        System.out.println("     Plano: " + resumo.getPlano().getNome());
        System.out.println("     Total de Porcoes no Pedido: " + resumo.getPedidoInicial().getColecaoItens().calcularTotalPorcoes());
        System.out.println("     Endereco: " + resumo.getEndereco().getResumoFormatado());
        System.out.println("     Status da Assinatura: " + resumo.getStatus());
        System.out.println("     Valor Total a Pagar: R$ " + String.format("%.2f", resumo.getValorTotal()));
    }

    public void exibirConfirmacaoSucesso(String protocolo, String previsaoEntrega) {
        System.out.println("  [Seq 55: viewAssinatura.exibirConfirmacaoSucesso] TELA DE SUCESSO:");
        System.out.println("     ================================================================");
        System.out.println("     ASSINATURA CONFIRMADA E ATIVA!");
        System.out.println("     Protocolo Gerado: " + protocolo);
        System.out.println("     Previsao da 1a Entrega: " + previsaoEntrega);
        System.out.println("     ================================================================");
    }

    public void exibirErroPagamento(String mensagem) {
        System.out.println("  [Seq A2: viewAssinatura.exibirErroPagamento] TELA (ERRO DE PAGAMENTO): " + mensagem);
    }
}
