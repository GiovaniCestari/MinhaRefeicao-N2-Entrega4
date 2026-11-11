package minharefeicao.entidade;

import minharefeicao.enums.Periodicidade;
import java.util.LinkedHashMap;
import java.util.Map;

public class Plano {
    private String id;
    private String nome;
    private Periodicidade periodicidade;
    private int duracaoDias;
    private int quantidadeRefeicoes;
    private double valor;

    private static final Map<String, Plano> PLANOS_DISPONIVEIS = new LinkedHashMap<>();

    static {
        PLANOS_DISPONIVEIS.put("PLANO_SEM_5", new Plano("PLANO_SEM_5", "Plano Semanal Compacto (5 refeicoes)", Periodicidade.SEMANAL, 7, 5, 149.90));
        PLANOS_DISPONIVEIS.put("PLANO_SEM_10", new Plano("PLANO_SEM_10", "Plano Semanal Completo (10 refeicoes)", Periodicidade.SEMANAL, 7, 10, 279.90));
        PLANOS_DISPONIVEIS.put("PLANO_MEN_20", new Plano("PLANO_MEN_20", "Plano Mensal Fit (20 refeicoes)", Periodicidade.MENSAL, 30, 20, 499.90));
    }

    public Plano(String id, String nome, Periodicidade periodicidade, int duracaoDias, int quantidadeRefeicoes, double valor) {
        this.id = id;
        this.nome = nome;
        this.periodicidade = periodicidade;
        this.duracaoDias = duracaoDias;
        this.quantidadeRefeicoes = quantidadeRefeicoes;
        this.valor = valor;
    }

    public static Plano buscarPlano(String idPlano) {
        Plano pl = PLANOS_DISPONIVEIS.getOrDefault(idPlano, PLANOS_DISPONIVEIS.get("PLANO_SEM_5"));
        System.out.println("  [Seq 16: pl:Plano.buscarPlano] Plano selecionado: " + pl.getNome() + " | Limite: " + pl.getQuantidadeRefeicoes() + " refeicoes | R$ " + String.format("%.2f", pl.getValor()));
        return pl;
    }

    public static Map<String, Plano> listarTodos() { return PLANOS_DISPONIVEIS; }
    public String getId() { return id; }
    public String getNome() { return nome; }
    public Periodicidade getPeriodicidade() { return periodicidade; }
    public int getDuracaoDias() { return duracaoDias; }
    public int getQuantidadeRefeicoes() { return quantidadeRefeicoes; }
    public double getValor() { return valor; }
}
