@echo off
chcp 65001 >nul
echo Compilando o projeto Minha Refeicao (Java)...
if not exist bin mkdir bin
javac -encoding UTF-8 -d bin src/minharefeicao/enums/*.java src/minharefeicao/excecao/*.java src/minharefeicao/entidade/*.java src/minharefeicao/colecao/*.java src/minharefeicao/persistencia/*.java src/minharefeicao/fronteira/*.java src/minharefeicao/controller/*.java src/minharefeicao/Main.java
if %errorlevel% neq 0 (
    echo Erro ao compilar. Verifique se o Java JDK esta instalado no PATH.
    pause
    exit /b %errorlevel%
)
echo Compilacao concluida com sucesso! Iniciando aplicacao...
java -cp bin minharefeicao.Main
pause
