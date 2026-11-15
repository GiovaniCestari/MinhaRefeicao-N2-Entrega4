package minharefeicao.persistencia;

import minharefeicao.entidade.Assinatura;
import minharefeicao.entidade.ItemPedido;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.Locale;

public class PersistenciaCSV {
    private static final String DIRETORIO = "dados";
    private static final String ARQUIVO_ASSINATURAS = DIRETORIO + "/assinaturas.csv";
    private static final String ARQUIVO_ITENS = DIRETORIO + "/itens_pedido.csv";

    public static void salvarAssinatura(Assinatura as) {
        try {
            Files.createDirectories(Paths.get(DIRETORIO));

            boolean criarCabecalhoAssinatura = !Files.exists(Paths.get(ARQUIVO_ASSINATURAS));
            try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(ARQUIVO_ASSINATURAS, true), StandardCharsets.UTF_8))) {
                if (criarCabecalhoAssinatura) {
                    pw.println("PROTOCOLO;CELULAR_ASSINANTE;ID_PLANO;NOME_PLANO;VALOR_TOTAL;STATUS_ASSINATURA;STATUS_PEDIDO;STATUS_PAGAMENTO;PREVISAO_ENTREGA;ENDERECO");
                }
                pw.printf(Locale.US, "%s;%s;%s;%s;%.2f;%s;%s;%s;%s;%s%n",
                        as.getProtocolo(),
                        as.getAssinante().getCelular(),
                        as.getPlano().getId(),
                        as.getPlano().getNome(),
                        as.getValorTotal(),
                        as.getStatus(),
                        as.getPedidoInicial().getStatus(),
                        as.getPagamento().getStatus(),
                        as.getPrevisaoFormatada(),
                        as.getEndereco().getResumoFormatado()
                );
            }

            boolean criarCabecalhoItens = !Files.exists(Paths.get(ARQUIVO_ITENS));
            try (PrintWriter pwItens = new PrintWriter(new OutputStreamWriter(new FileOutputStream(ARQUIVO_ITENS, true), StandardCharsets.UTF_8))) {
                if (criarCabecalhoItens) {
                    pwItens.println("PROTOCOLO_ASSINATURA;ID_PEDIDO;ID_REFEICAO;NOME_REFEICAO;CATEGORIA;QUANTIDADE");
                }
                for (ItemPedido item : as.getPedidoInicial().getColecaoItens().getItens()) {
                    pwItens.printf("%s;%d;%s;%s;%s;%d%n",
                            as.getProtocolo(),
                            as.getPedidoInicial().getId(),
                            item.getRefeicao().getId(),
                            item.getRefeicao().getNome(),
                            item.getRefeicao().obterDescricaoCategoria(),
                            item.getQuantidade()
                    );
                }
            }

            System.out.println("  [Seq 61: PersistenciaCSV.salvarAssinatura] Dados persistidos com sucesso em '" + ARQUIVO_ASSINATURAS + "' e '" + ARQUIVO_ITENS + "'!");
        } catch (IOException e) {
            System.err.println("  [Erro de Persistencia CSV]: " + e.getMessage());
        }
    }

    public static void exibirConteudoCSV() {
        System.out.println("\n================================================================================");
        System.out.println("               LEITURA DOS ARQUIVOS DE PERSISTENCIA (.CSV)                      ");
        System.out.println("================================================================================");
        lerArquivo(ARQUIVO_ASSINATURAS);
        System.out.println("--------------------------------------------------------------------------------");
        lerArquivo(ARQUIVO_ITENS);
        System.out.println("================================================================================\n");
    }

    private static void lerArquivo(String caminho) {
        Path path = Paths.get(caminho);
        System.out.println(">>> Arquivo: " + caminho);
        if (!Files.exists(path)) {
            System.out.println("    (Arquivo ainda nao gerado. Execute o Fluxo Principal primeiro!)");
            return;
        }
        try {
            List<String> linhas = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String linha : linhas) {
                System.out.println("    " + linha);
            }
        } catch (IOException e) {
            System.out.println("    Erro ao ler arquivo: " + e.getMessage());
        }
    }
}
