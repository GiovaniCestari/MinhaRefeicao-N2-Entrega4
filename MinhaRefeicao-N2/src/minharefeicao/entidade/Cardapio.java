package minharefeicao.entidade;

import minharefeicao.colecao.CatalogoRefeicoes;
import java.util.List;

public class Cardapio {
    private Long id;
    private String periodoSemana;
    private boolean atualizado;
    private CatalogoRefeicoes catalogoRefeicoes;

    public Cardapio(Long id, String periodoSemana, CatalogoRefeicoes catalogoRefeicoes) {
        this.id = id;
        this.periodoSemana = periodoSemana;
        this.atualizado = true;
        this.catalogoRefeicoes = catalogoRefeicoes;
    }

    public List<Refeicao> buscarCompativeis(List<PreferenciaAlimentar> listaPref) {
        System.out.println("  [Seq 21: ca:Cardapio.buscarCompativeis] Consultando colecao de refeicoes do cardapio (" + periodoSemana + ")...");
        return catalogoRefeicoes.filtrarPorPreferencias(listaPref);
    }

    public CatalogoRefeicoes getCatalogoRefeicoes() { return catalogoRefeicoes; }
}
