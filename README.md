# Plataforma Minha Refeição — N2 (Entrega 4: Implementação do Cenário em Java)

Implementação prática em **Java** do cenário de caso de uso **"Assinar Plano de Refeições"**, traduzindo fielmente o **Diagrama de Sequência UML (Passos 1 a 62 e Fluxos Alternativos FA1, FA2 e FA3)** e o **Diagrama de Classes de Projeto** nas camadas de **Fronteira (`<<Fronteira>>`)**, **Controle (`<<Controller>>`)**, **Entidade (`<<Entidade>>`)**, **Coleções (`<<Collection>>`)**, **Exceção (`<<Exception>>`)** e **Persistência em Arquivo CSV (`<<Persistence>>`)**.

## Integrantes do Grupo
* **Giovani de Oliveira Cestari** — RA: 10738542
* **Guilherme Ruotolo da Costa** — RA: 10736376
* **Julio Cesar Nunes Mechi** — RA: 10737836

**Disciplina:** Projeto de Software — Universidade Presbiteriana Mackenzie  
**Professora:** Profa. Ana Claudia Rossi  

---

## 🔗 Links da Entrega 4
* 📖 **Documentação Completa na Wiki do GitHub (Entrega 4):** [Acessar Wiki da Entrega 4](https://github.com/GiovaniCestari/MinhaRefeicao-N2-Entrega4/wiki)
* 🎥 **Vídeo de Demonstração (UML + Código Java + Execução):** https://youtu.be/QE0vsf33wI4
* 💻 **Código-Fonte Java:** [`src/minharefeicao/`](src/minharefeicao)
* 💾 **Arquivos de Persistência CSV:** [`dados/assinaturas.csv`](dados/assinaturas.csv) e [`dados/itens_pedido.csv`](dados/itens_pedido.csv)

---

## Arquitetura do Projeto (Padrão BCE + Coleções + Persistência CSV)

O código-fonte está organizado no diretório `src/minharefeicao/` espelhando 1:1 os pacotes e estereótipos da modelagem UML:

1. **`minharefeicao.fronteira` (`<<Fronteira>>`):**
   * `TelaAssinatura.java`: Recebe os estímulos de negócio do Ator (`solicitarAutenticacaoSMS`, `confirmarAutenticacaoSMS`, `selecionarPlanoEPreferencias`, `incluirRefeicaoNoPedido`, `registrarLocalEntrega`, `efetuarPagamentoAssinatura`), delega ao controlador e exibe os retornos visuais e alertas (`FA1`, `FA2` e `FA3`).
   * `OperadoraCartao.java`: Fronteira de integração externa para autorização da transação de cartão de crédito (`autorizarTransacao`).
2. **`minharefeicao.controller` (`<<Controller>>`):**
   * `CtrlAssinatura.java`: Coordena o fluxo do caso de uso, instanciando e delegando operações às entidades, coleções e camada de persistência.
3. **`minharefeicao.entidade` (`<<Entidade>>`):**
   * Classes de domínio encapsuladas: `Assinante`, `CodigoConfirmacao`, `PreferenciaAlimentar`, `Plano`, `Assinatura`, `Pedido`, `ItemPedido`, `Cardapio`, `Endereco`, `CartaoCredito`, `Pagamento` e a hierarquia de herança/polimorfismo com a superclasse abstrata `Refeicao` e suas subclasses `PratoPrincipal`, `Acompanhamento` e `Sobremesa`.
4. **`minharefeicao.colecao` (`<<Collection>>`):**
   * `CatalogoRefeicoes.java`: Multiobjeto responsável por manter e filtrar as refeições compatíveis com as preferências do assinante.
   * `ColecaoItensPedido.java`: Multiobjeto responsável por agrupar (e remover, em caso de excesso no `FA2`) os itens selecionados durante o `loop` de montagem do pedido.
5. **`minharefeicao.excecao` (`<<Exception>>`):**
   * `LimiteRefeicoesExcedidoException.java`: Exceção de negócio lançada quando a quantidade de porções ultrapassa o limite permitido pelo plano contratado (`FA2`).
6. **`minharefeicao.persistencia` (`<<Persistence>>`):**
   * `PersistenciaCSV.java`: Realiza a persistência em disco nos arquivos `dados/assinaturas.csv` e `dados/itens_pedido.csv` após a ativação da assinatura, além de permitir a leitura dos registros salvos.

---

## Como Executar em 1 Clique

### Opção A: No Windows (Dois cliques)
Dê dois cliques no arquivo **`executar_windows.bat`** na raiz do projeto. Ele compila todos os arquivos `.java` automaticamente e abre o menu interativo.

### Opção B: No Git Bash / Terminal do VS Code
```bash
bash executar_gitbash.sh
