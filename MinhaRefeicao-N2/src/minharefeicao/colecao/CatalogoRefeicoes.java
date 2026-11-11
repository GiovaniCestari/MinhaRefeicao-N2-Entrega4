package minharefeicao.colecao;

import minharefeicao.entidade.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatalogoRefeicoes {
    private List<Refeicao> refeicoes;

    public CatalogoRefeicoes() {
        this.refeicoes = new ArrayList<>();
        inicializarCardapioPadrao();
    }

    private void inicializarCardapioPadrao() {
        PreferenciaAlimentar lowCarb = new PreferenciaAlimentar(1L, "Low Carb", "Baixo teor de carboidratos");
        PreferenciaAlimentar proteico = new PreferenciaAlimentar(2L, "Proteico", "Alto teor de proteinas");
        PreferenciaAlimentar vegetariano = new PreferenciaAlimentar(3L, "Vegetariano", "Sem carnes");
        PreferenciaAlimentar zeroLactose = new PreferenciaAlimentar(4L, "Zero Lactose", "Sem derivados lacteos");

        refeicoes.add(new PratoPrincipal("REF01", "File Mignon Grelhado ao Molho de Ervas", 420, "Carne Bovina", 350, Arrays.asList(lowCarb, proteico, zeroLactose)));
        refeicoes.add(new PratoPrincipal("REF02", "Frango Grelhado com Crosta de Gergelim", 380, "Peito de Frango", 320, Arrays.asList(lowCarb, proteico, zeroLactose)));
        refeicoes.add(new PratoPrincipal("REF03", "Salmao Assado com Alecrim", 460, "Salmao", 300, Arrays.asList(lowCarb, proteico, zeroLactose)));
        refeicoes.add(new Acompanhamento("REF04", "Mix de Brocolis, Couve-Flor e Cenoura no Vapor", 110, 180, Arrays.asList(lowCarb, proteico, vegetariano, zeroLactose)));
        refeicoes.add(new Acompanhamento("REF05", "Pure de Mandioquinha Fit com Azeite Trufado", 190, 150, Arrays.asList(proteico, vegetariano, zeroLactose)));
        refeicoes.add(new Sobremesa("REF06", "Mousse de Cacau 70% com Chia", 140, true, Arrays.asList(lowCarb, proteico, vegetariano, zeroLactose)));
    }

    public List<Refeicao> filtrarPorPreferencias(List<PreferenciaAlimentar> listaPref) {
        System.out.println("  [Seq 21: :Refeicao(CatalogoRefeicoes).filtrarPorPreferencias] Percorrendo colecao de refeicoes...");
        List<Refeicao> filtradas = new ArrayList<>();
        for (Refeicao r : refeicoes) {
            boolean compativel = r.verificarCompatibilidade(listaPref);
            if (compativel) {
                filtradas.add(r);
            }
        }
        System.out.println("  [Seq 22: r:Refeicao.verificarCompatibilidade] " + filtradas.size() + " refeicoes compativeis encontradas.");
        return filtradas;
    }

    public Refeicao buscarRefeicao(String idRefeicao) {
        for (Refeicao r : refeicoes) {
            if (r.getId().equalsIgnoreCase(idRefeicao)) {
                System.out.println("  [Seq 26: :Refeicao(CatalogoRefeicoes).buscarRefeicao] Refeicao localizada: " + r.getId() + " - " + r.getNome());
                return r;
            }
        }
        return refeicoes.get(0);
    }
}
