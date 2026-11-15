# Plataforma Minha Refeição — N2 (Entrega 4: Implementação do Cenário em Java)

Implementação prática em **Java** do cenário de caso de uso **"Assinar Plano de Refeições"**, traduzindo fielmente o **Diagrama de Sequência UML** e o **Diagrama de Classes de Projeto** nas camadas de **Fronteira (`<<Fronteira>>`)**, **Controle (`<<Controller>>`)**, **Entidade (`<<Entidade>>`)**, **Coleções (`<<Collection>>`)** e **Persistência em Arquivo CSV (`<<Persistence>>`)**.

## Integrantes do Grupo
* **Giovani de Oliveira Cestari** — RA: 10738542
* **Guilherme Ruotolo da Costa** — RA: 10736376
* **Julio Cesar Nunes Mechi** — RA: 10737836

**Disciplina:** Projeto de Software — Universidade Presbiteriana Mackenzie  
**Professora:** Profa. Ana Claudia Rossi  

---

## Como Executar em 1 Clique

### Opção A: No Windows (Dois cliques)
Basta dar dois cliques no arquivo **`executar_windows.bat`** na raiz do projeto. Ele compila todos os arquivos `.java` automaticamente e abre o menu interativo.

### Opção B: No Git Bash / Terminal do VS Code
```bash
bash executar_gitbash.sh
```
Ou para rodar toda a bateria de testes (Fluxo Principal + Fluxos Alternativos FA1, FA2 e FA3 + Leitura do CSV) de uma só vez sem precisar digitar nada:
```bash
mkdir -p bin && javac -encoding UTF-8 -d bin $(find src -name "*.java") && java -cp bin minharefeicao.Main --auto-test
```
