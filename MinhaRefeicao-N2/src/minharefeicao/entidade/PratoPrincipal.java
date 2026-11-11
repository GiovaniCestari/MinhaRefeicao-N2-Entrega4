package minharefeicao.entidade;

import java.util.List;

public class PratoPrincipal extends Refeicao {
    private String proteinaPrincipal;
    private int pesoGramas;

    public PratoPrincipal(String id, String nome, int calorias, String proteinaPrincipal, int pesoGramas, List<PreferenciaAlimentar> preferencias) {
        super(id, nome, calorias, preferencias);
        this.proteinaPrincipal = proteinaPrincipal;
        this.pesoGramas = pesoGramas;
    }

    @Override
    public String obterDescricaoCategoria() {
        return "Prato Principal [Proteina: " + proteinaPrincipal + ", " + pesoGramas + "g, " + calorias + " kcal]";
    }
}
