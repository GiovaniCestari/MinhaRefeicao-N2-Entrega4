package minharefeicao.entidade;

import java.time.LocalDateTime;

public class CodigoConfirmacao {
    private String codigo;
    private String celularDestino;
    private LocalDateTime dataHoraEnvio;
    private int tentativas;
    private boolean validado;
    public static final int LIMITE_TENTATIVAS = 3;

    private CodigoConfirmacao(String celularDestino, String codigo) {
        this.celularDestino = celularDestino;
        this.codigo = codigo;
        this.dataHoraEnvio = LocalDateTime.now();
        this.tentativas = 0;
        this.validado = false;
    }

    public static CodigoConfirmacao criarCodigo(String celular) {
        String codigoGerado = "123456";
        System.out.println("  [Seq 4: <<create>> c:CodigoConfirmacao] Codigo SMS '" + codigoGerado + "' gerado para " + celular);
        return new CodigoConfirmacao(celular, codigoGerado);
    }

    public boolean validar(String codigoInformado) {
        this.tentativas++;
        if (this.tentativas > LIMITE_TENTATIVAS) {
            this.validado = false;
            return false;
        }
        this.validado = this.codigo.equals(codigoInformado);
        System.out.println("  [Seq 9: c:CodigoConfirmacao.validar] Tentativa " + tentativas + "/" + LIMITE_TENTATIVAS + " -> validado = " + validado);
        return this.validado;
    }

    public String getCodigo() { return codigo; }
    public int getTentativas() { return tentativas; }
    public boolean isValidado() { return validado; }
    public boolean atingiuLimiteTentativas() { return tentativas >= LIMITE_TENTATIVAS; }
}
