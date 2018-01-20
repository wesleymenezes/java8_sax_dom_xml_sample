Programa: recolhimento_iss.jar
Descrição: Cálculo de arrecadação de ISS com base nos arquivos XML recebidos.


1. Compilação no Windows
Ambiente com JDK 1.8 e Eclipse instalados.
Descompactar o arquivo "TR.zip" e importar o projeto no Eclipse.
Realizar "Build" do projeto "RecolhimentoISS" pelo Eclipse e Exportar "Runnable Jar file" com nome "recolhimento_iss.jar" para gerar novo pacote para execução.
IMPORTANTE: Caso desejável, o arquivo "TR.zip" já contém em seu interior um arquivo compilado de acordo com o procedimento.

2. Instalação
Não há necessidade de realizar qualquer configuração para instalação (o arquivo de configuração "params\recolhimentoiss.properties" dos diretórios de entrada e saída pode ser configurado conforme indicado no item anterior).
De forma simplificada, os arquivos XML de entrada deverão estar no diretório "notas\pendentes" e o arquivo resultado da execução estará no diretório "notas\processadas".

1. Execução na linha de comando.
Abrir a linha de comando do DOS e no diretório onde o pacote "recolhimento_iss.jar" foi gerado executar:
C:\recolhimento_iss.jar
ou
C:\java -classpath recolhimento_iss.jar com.thomsonreuters.recruitment.batch.impostos.RecolhimentoISS
