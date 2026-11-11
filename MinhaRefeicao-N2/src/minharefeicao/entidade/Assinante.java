package minharefeicao.entidade;

import java.util.ArrayList;
import java.util.List;

public class Assinante {
    private Long id;
    private String celular;
    private boolean ativo;
    private List<CodigoConfirmacao> codigosConfirmacao;
    private List<PreferenciaAlimentar> preferencias;

    public Assinante(Long id, String celular) {
        this.id = id;
        this.celular = celular;
        this.ativo = false;
        this.codigosConfirmacao = new ArrayList<>();
        this.preferencias = new ArrayList<>();
    }

    public static Assinante buscarPorCelular(String celular) {
        System.out.println("  [Seq 3: a:Assinante.buscarPorCelular] Assinante localizado/inicializado para o celular " + celular);
        return new Assinante(1L, celular);
    }

    public void adicionarCodigo(CodigoConfirmacao codigo) {
        this.codigosConfirmacao.add(codigo);
    }

    public boolean autenticar() {
        this.ativo = true;
        System.out.println("  [Seq 11: a:Assinante.autenticar] Assinante autenticado com sucesso (ativo = true)");
        return this.ativo;
    }

    public void vincularPreferencias(List<PreferenciaAlimentar> listaPref) {
        this.preferencias.clear();
        this.preferencias.addAll(listaPref);
        System.out.println("  [Seq 15: a:Assinante.vincularPreferencias] Preferencias vinculadas: " + listaPref);
    }

    public Long getId() { return id; }
    public String getCelular() { return celular; }
    public boolean isAtivo() { return ativo; }
    public List<PreferenciaAlimentar> getPreferencias() { return preferencias; }
}
