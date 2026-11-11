package minharefeicao.entidade;

public class PreferenciaAlimentar {
    private Long id;
    private String nome;
    private String descricao;

    public PreferenciaAlimentar(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }

    @Override
    public String toString() {
        return nome;
    }
}
