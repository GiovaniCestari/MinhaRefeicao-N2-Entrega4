package minharefeicao.entidade;

import java.util.ArrayList;
import java.util.List;

public abstract class Refeicao {
    protected String id;
    protected String nome;
    protected int calorias;
    protected List<PreferenciaAlimentar> preferenciasCompativeis;

    public Refeicao(String id, String nome, int calorias, List<PreferenciaAlimentar> preferenciasCompativeis) {
        this.id = id;
        this.nome = nome;
        this.calorias = calorias;
        this.preferenciasCompativeis = new ArrayList<>(preferenciasCompativeis);
    }

    public boolean verificarCompatibilidade(List<PreferenciaAlimentar> listaPref) {
        if (listaPref == null || listaPref.isEmpty()) return true;
        for (PreferenciaAlimentar prefAssinante : listaPref) {
            for (PreferenciaAlimentar prefPrato : preferenciasCompativeis) {
                if (prefPrato.getNome().equalsIgnoreCase(prefAssinante.getNome())) {
                    return true;
                }
            }
        }
        return false;
    }

    public abstract String obterDescricaoCategoria();

    public String getId() { return id; }
    public String getNome() { return nome; }
    public int getCalorias() { return calorias; }
}
