package minharefeicao;

import minharefeicao.controller.CtrlAssinatura;
import minharefeicao.entidade.PreferenciaAlimentar;
import minharefeicao.fronteira.TelaAssinatura;
import minharefeicao.persistencia.PersistenciaCSV;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("--auto-test")) {
            executarFluxoPrincipalAutomatico();
            executarFluxosAlternativosAutomatico();
            PersistenciaCSV.exibirConteudoCSV();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n================================================================================");
            System.out.println("   PLATAFORMA MINHA REFEICAO - ENTREGA 4 (IMPLEMENTACAO DO CASO DE USO EM JAVA) ");
            System.out.println("================================================================================");
            System.out.println(" [1] Executar Demonstracao Automatica: FLUXO PRINCIPAL COMPLETO (Passos 1 a 55 + CSV)");
            System.out.println(" [2] Executar Demonstracao Automatica: FLUXOS ALTERNATIVOS (A1: SMS Invalido/Limite | A2: Cartao Recusado)");
            System.out.println(" [3] Executar Simulacao Interativa (Digitar dados passo a passo no console)");
            System.out.println(" [4] Exibir Dados Persistidos nos Arquivos CSV (dados/assinaturas.csv e itens_pedido.csv)");
            System.out.println(" [0] Sair");
            System.out.print("Escolha uma opcao: ");

            if (!scanner.hasNextLine()) break;
            String opcao = scanner.nextLine().trim();
            switch (opcao) {
                case "1":
                    executarFluxoPrincipalAutomatico();
                    break;
                case "2":
                    executarFluxosAlternativosAutomatico();
                    break;
                case "3":
                    executarFluxoInterativo(scanner);
                    break;
                case "4":
                    PersistenciaCSV.exibirConteudoCSV();
                    break;
                case "0":
                    executando = false;
                    System.out.println("Encerrando aplicacao Minha Refeicao.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    public static void executarFluxoPrincipalAutomatico() {
        System.out.println("\n>>> INICIANDO SIMULACAO DO FLUXO PRINCIPAL (CENARIO: ASSINAR PLANO DE REFEICOES) <<<");
        CtrlAssinatura ctrl = new CtrlAssinatura();
        TelaAssinatura view = new TelaAssinatura(ctrl);

        view.solicitarAutenticacaoSMS("(11) 99876-5432");
        view.confirmarAutenticacaoSMS("(11) 99876-5432", "123456");

        List<PreferenciaAlimentar> prefs = Arrays.asList(
                new PreferenciaAlimentar(1L, "Low Carb", "Baixo carboidrato"),
                new PreferenciaAlimentar(2L, "Proteico", "Alta proteina")
        );
        view.selecionarPlanoEPreferencias("PLANO_SEM_5", prefs);

        view.incluirRefeicaoNoPedido("REF01", 2);
        view.incluirRefeicaoNoPedido("REF02", 2);
        view.incluirRefeicaoNoPedido("REF06", 1);

        Map<String, String> dadosEnd = new HashMap<>();
        dadosEnd.put("logradouro", "Rua da Consolacao");
        dadosEnd.put("numero", "930");
        dadosEnd.put("complemento", "Bloco B");
        dadosEnd.put("bairro", "Consolacao");
        dadosEnd.put("cidade", "Sao Paulo");
        dadosEnd.put("uf", "SP");
        dadosEnd.put("cep", "01302-907");
        view.registrarLocalEntrega(dadosEnd);

        view.efetuarPagamentoAssinatura("4532 1122 3344 8899", "GIOVANI O CESTARI", "10/2030");
    }

    public static void executarFluxosAlternativosAutomatico() {
        System.out.println("\n>>> INICIANDO SIMULACAO DOS FLUXOS ALTERNATIVOS E DE EXCECAO (A1 e A2) <<<");
        CtrlAssinatura ctrl = new CtrlAssinatura();
        TelaAssinatura view = new TelaAssinatura(ctrl);

        System.out.println("\n--- [FLUXO ALTERNATIVO A1: Codigo SMS Invalido e Bloqueio por Limite de Tentativas] ---");
        view.solicitarAutenticacaoSMS("(11) 91234-5678");
        view.confirmarAutenticacaoSMS("(11) 91234-5678", "000000");
        view.confirmarAutenticacaoSMS("(11) 91234-5678", "999999");
        view.confirmarAutenticacaoSMS("(11) 91234-5678", "111111");

        System.out.println("\n--- [FLUXO ALTERNATIVO A2: Pagamento Nao Autorizado pela Operadora de Cartao] ---");
        view.solicitarAutenticacaoSMS("(11) 91234-5678");
        view.confirmarAutenticacaoSMS("(11) 91234-5678", "123456");

        List<PreferenciaAlimentar> prefs = Collections.singletonList(new PreferenciaAlimentar(1L, "Low Carb", "Baixo carboidrato"));
        view.selecionarPlanoEPreferencias("PLANO_SEM_5", prefs);
        view.incluirRefeicaoNoPedido("REF01", 3);
        view.incluirRefeicaoNoPedido("REF04", 2);

        Map<String, String> dadosEnd = new HashMap<>();
        dadosEnd.put("logradouro", "Av. Paulista");
        dadosEnd.put("numero", "1000");
        dadosEnd.put("complemento", "Conj 101");
        dadosEnd.put("bairro", "Bela Vista");
        dadosEnd.put("cidade", "Sao Paulo");
        dadosEnd.put("uf", "SP");
        dadosEnd.put("cep", "01310-100");
        view.registrarLocalEntrega(dadosEnd);

        view.efetuarPagamentoAssinatura("5412 7500 0000 0000", "CLIENTE TESTE RECUSA", "05/2028");
    }

    private static void executarFluxoInterativo(Scanner scanner) {
        CtrlAssinatura ctrl = new CtrlAssinatura();
        TelaAssinatura view = new TelaAssinatura(ctrl);

        System.out.print("\n1. Digite seu celular para receber o codigo SMS: ");
        String celular = scanner.nextLine();
        view.solicitarAutenticacaoSMS(celular);

        boolean autenticado = false;
        while (!autenticado) {
            System.out.print("2. Digite o codigo SMS recebido (dica: 123456): ");
            String codigo = scanner.nextLine();
            autenticado = ctrl.validarCodigoSMS(celular, codigo);
            if (!autenticado) {
                System.out.print("Deseja solicitar um novo codigo SMS? (s/n): ");
                if (scanner.nextLine().equalsIgnoreCase("s")) {
                    view.solicitarAutenticacaoSMS(celular);
                }
            }
        }

        System.out.print("\n3. Digite o ID do Plano (PLANO_SEM_5, PLANO_SEM_10 ou PLANO_MEN_20): ");
        String idPlano = scanner.nextLine().trim().toUpperCase();
        if (idPlano.isEmpty()) idPlano = "PLANO_SEM_5";

        List<PreferenciaAlimentar> prefs = Collections.singletonList(new PreferenciaAlimentar(1L, "Low Carb", "Baixo Carboidrato"));
        view.selecionarPlanoEPreferencias(idPlano, prefs);

        while (ctrl.getSaldoRestanteAtual() > 0) {
            System.out.print("\n4. [Loop Cardapio - Faltam " + ctrl.getSaldoRestanteAtual() + " refeicoes] Digite o ID da Refeicao (REF01 a REF06): ");
            String idRef = scanner.nextLine().trim().toUpperCase();
            System.out.print("   Quantidade de porcoes: ");
            int qtde = Integer.parseInt(scanner.nextLine().trim());
            view.incluirRefeicaoNoPedido(idRef, qtde);
        }

        Map<String, String> dadosEnd = new HashMap<>();
        System.out.print("\n5. Digite o Logradouro de entrega: ");
        dadosEnd.put("logradouro", scanner.nextLine());
        System.out.print("   Numero: ");
        dadosEnd.put("numero", scanner.nextLine());
        dadosEnd.put("complemento", "Apto");
        dadosEnd.put("bairro", "Vila Mariana");
        dadosEnd.put("cidade", "Sao Paulo");
        dadosEnd.put("uf", "SP");
        System.out.print("   CEP (8 digitos): ");
        dadosEnd.put("cep", scanner.nextLine());
        view.registrarLocalEntrega(dadosEnd);

        boolean pago = false;
        while (!pago) {
            System.out.print("\n6. Digite o Numero do Cartao (termine em 0000 para testar recusa, ou qualquer outro para aprovar): ");
            String numCartao = scanner.nextLine();
            System.out.print("   Nome do Titular: ");
            String titular = scanner.nextLine();
            System.out.print("   Validade (MM/AAAA): ");
            String validade = scanner.nextLine();
            pago = ctrl.processarPagamento(numCartao, titular, validade);
        }
    }
}
