package minharefeicao.entidade;

public class CartaoCredito {
    private String numero;
    private String nomeTitular;
    private String validade;

    private CartaoCredito(String numero, String nomeTitular, String validade) {
        this.numero = numero;
        this.nomeTitular = nomeTitular;
        this.validade = validade;
    }

    public static CartaoCredito criar(String numero, String titular, String validade) {
        System.out.println("  [Seq 50: <<create>> cc:CartaoCredito.criar] Instanciando CartaoCredito do titular " + titular);
        CartaoCredito cc = new CartaoCredito(numero, titular, validade);
        cc.validarDadosCartao();
        return cc;
    }

    public boolean validarDadosCartao() {
        boolean valido = numero != null && numero.replaceAll("\\D", "").length() >= 13
                && nomeTitular != null && !nomeTitular.trim().isEmpty();
        System.out.println("  [Seq 51: cc:CartaoCredito.validarDadosCartao] Dados formais do cartao validados = " + valido);
        return valido;
    }

    public String getNumero() { return numero; }
    public String getNomeTitular() { return nomeTitular; }
    public String getValidade() { return validade; }
}
