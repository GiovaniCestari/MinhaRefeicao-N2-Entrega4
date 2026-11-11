package minharefeicao.entidade;

import java.util.List;

public class Acompanhamento extends Refeicao {
    private int porcaoGramas;

    public Acompanhamento(String id, String nome, int calorias, int porcaoGramas, List<PreferenciaAlimentar> preferencias) {
        super(id, nome, calorias, preferencias);
        this.porcaoGramas = porcaoGramas;
    }

    @Override
    public String obterDescricaoCategoria() {
        return "Acompanhamento [Porcao: " + porcaoGramas + "g, " + calorias + " kcal]";
    }
}
