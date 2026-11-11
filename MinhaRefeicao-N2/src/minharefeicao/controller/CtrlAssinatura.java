package minharefeicao.controller;

import minharefeicao.colecao.CatalogoRefeicoes;
import minharefeicao.entidade.*;
import minharefeicao.fronteira.OperadoraCartao;
import minharefeicao.fronteira.TelaAssinatura;
import minharefeicao.persistencia.PersistenciaCSV;

import java.util.List;
import java.util.Map;

public class CtrlAssinatura {
    private TelaAssinatura viewAssinatura;
    private OperadoraCartao opCartao;
    private Assinante assinanteAtual;
    private CodigoConfirmacao codigoAtual;
    private Assinatura assinaturaAtual;
    private Cardapio cardapioSemanal;
    private CatalogoRefeicoes catalogoRefeicoes;

    public CtrlAssinatura() {
        this.catalogoRefeicoes = new CatalogoRefeicoes();
        this.cardapioSemanal = new Cardapio(101L, "Semana Atual - Nov/2026", this.catalogoRefeicoes);
        this.opCartao = new OperadoraCartao("Operadora Visa/Mastercard Gateway");
    }

    public void setViewAssinatura(TelaAssinatura viewAssinatura) {
        this.viewAssinatura = viewAssinatura;
    }

    public void solicitarCodigoSMS(String celular) {
        System.out.println("  [Seq 2: ctrlAssinatura.solicitarCodigoSMS] Iniciando solicitacao de codigo SMS...");
        this.assinanteAtual = Assinante.buscarPorCelular(celular);
        this.codigoAtual = CodigoConfirmacao.criarCodigo(celular);
        this.assinanteAtual.adicionarCodigo(this.codigoAtual);
        this.viewAssinatura.confirmarEnvioSMS();
    }

    public boolean validarCodigoSMS(String celular, String codigoInformado) {
        System.out.println("  [Seq 8: ctrlAssinatura.validarCodigoSMS] Validando codigo informado...");
        boolean validado = this.codigoAtual.validar(codigoInformado);

        if (validado) {
            this.assinanteAtual.autenticar();
            this.viewAssinatura.exibirPlanosDisponiveis();
            return true;
        } else {
            if (!this.codigoAtual.atingiuLimiteTentativas()) {
                this.viewAssinatura.exibirErroSMS("Codigo invalido (Tentativa " + codigoAtual.getTentativas() + "/"
                        + CodigoConfirmacao.LIMITE_TENTATIVAS + "). Tente novamente.");
            } else {
                this.viewAssinatura.exibirErroSMS("Limite maximo de " + CodigoConfirmacao.LIMITE_TENTATIVAS
                        + " tentativas excedido! Solicite um novo codigo SMS.");
            }
            return false;
        }
    }

    public void iniciarAssinatura(String idPlano, List<PreferenciaAlimentar> listaPref) {
        System.out.println("  [Seq 14: ctrlAssinatura.iniciarAssinatura] Coordenando criacao da assinatura e filtro de cardapio...");
        this.assinanteAtual.vincularPreferencias(listaPref);
        Plano pl = Plano.buscarPlano(idPlano);
        this.assinaturaAtual = Assinatura.criar(this.assinanteAtual, pl);

        List<Refeicao> catalogoFiltrado = this.cardapioSemanal.buscarCompativeis(listaPref);
        this.viewAssinatura.exibirCardapio(catalogoFiltrado, pl.getQuantidadeRefeicoes());
    }

    public int addRefeicao(String idRefeicao, int qtde) {
        System.out.println("  [Seq 25: ctrlAssinatura.addRefeicao] Buscando refeicao na colecao e adicionando ao pedido...");
        Refeicao r = this.catalogoRefeicoes.buscarRefeicao(idRefeicao);
        int limitePlano = this.assinaturaAtual.getPlano().getQuantidadeRefeicoes();
        int saldoRestante = this.assinaturaAtual.getPedidoInicial().adicionarItem(r, qtde, limitePlano);
        this.viewAssinatura.atualizarBarraProgresso(saldoRestante);
        return saldoRestante;
    }

    public void definirEnderecoEntrega(Map<String, String> dadosEndereco) {
        System.out.println("  [Seq 36: ctrlAssinatura.definirEnderecoEntrega] Instanciando e vinculando endereco...");
        Endereco en = Endereco.criar(dadosEndereco);
        this.assinaturaAtual.vincularEndereco(en);
        this.viewAssinatura.exibirResumoParaPagamento(this.assinaturaAtual);
    }

    public boolean processarPagamento(String numero, String titular, String validade) {
        System.out.println("  [Seq 43: ctrlAssinatura.processarPagamento] Processando pagamento da assinatura...");
        CartaoCredito cc = CartaoCredito.criar(numero, titular, validade);
        Pagamento pg = Pagamento.criar(this.assinaturaAtual.getValorTotal(), cc);
        this.assinaturaAtual.setPagamento(pg);

        boolean autorizado = this.opCartao.autorizarTransacao(pg);

        if (autorizado) {
            pg.confirmarAutorizacao();
            this.assinaturaAtual.getPedidoInicial().aprovarPedido();
            String protocolo = this.assinaturaAtual.ativarAssinatura();
            PersistenciaCSV.salvarAssinatura(this.assinaturaAtual);
            this.viewAssinatura.exibirConfirmacaoSucesso(protocolo, this.assinaturaAtual.getPrevisaoFormatada());
            return true;
        } else {
            pg.registrarRecusa();
            this.viewAssinatura.exibirErroPagamento("Pagamento nao autorizado pela operadora. Informe outro cartao de credito.");
            return false;
        }
    }

    public int getSaldoRestanteAtual() {
        if (assinaturaAtual == null) return 0;
        int limite = assinaturaAtual.getPlano().getQuantidadeRefeicoes();
        int atual = assinaturaAtual.getPedidoInicial().getColecaoItens().calcularTotalPorcoes();
        return Math.max(0, limite - atual);
    }
}
