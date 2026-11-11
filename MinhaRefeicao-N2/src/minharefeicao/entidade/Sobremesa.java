package minharefeicao.entidade;

import java.util.List;

public class Sobremesa extends Refeicao {
    private boolean zeroAcucar;

    public Sobremesa(String id, String nome, int calorias, boolean zeroAcucar, List<PreferenciaAlimentar> preferencias) {
        super(id, nome, calorias, preferencias);
        this.zeroAcucar = zeroAcucar;
    }

    @Override
    public String obterDescricaoCategoria() {
        return "Sobremesa [" + (zeroAcucar ? "Zero Acucar" : "Tradicional") + ", " + calorias + " kcal]";
    }
}
