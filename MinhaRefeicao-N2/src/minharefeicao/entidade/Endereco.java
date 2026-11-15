package minharefeicao.entidade;

import java.util.Map;

public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    private Endereco(Map<String, String> dados) {
        this.logradouro = dados.getOrDefault("logradouro", "");
        this.numero = dados.getOrDefault("numero", "");
        this.complemento = dados.getOrDefault("complemento", "");
        this.bairro = dados.getOrDefault("bairro", "");
        this.cidade = dados.getOrDefault("cidade", "");
        this.uf = dados.getOrDefault("uf", "");
        this.cep = dados.getOrDefault("cep", "");
    }

    public static Endereco criar(Map<String, String> dadosEndereco) {
        System.out.println("  [Seq 41: <<create>> en:Endereco.criar] Instanciando endereco de entrega...");
        Endereco en = new Endereco(dadosEndereco);
        en.validarCEP();
        return en;
    }

    public boolean validarCEP() {
        boolean valido = this.cep != null && this.cep.replaceAll("\\D", "").length() == 8;
        System.out.println("  [Seq 42: en:Endereco.validarCEP] CEP " + this.cep + " validado = " + valido);
        return valido;
    }

    public String getResumoFormatado() {
        return logradouro + ", " + numero + " (" + complemento + ") - " + bairro + ", " + cidade + "/" + uf + " - CEP: " + cep;
    }
}
