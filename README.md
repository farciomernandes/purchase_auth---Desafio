# Instruções para executar o projeto


## Observação

<p> Deve possuir o maven além do java </p>

<p> Todo o código está na pasta src/main/java </p>


## Sobre o projeto

1. Arquitetura:{
   
   <p>Neste projeto tentei utilizar as bases que aprendi sobre a arquitetura Domain Driver Design e SOLID.</p>
   <p>A pasta modules serve para separar os arquivos de acordo com seu dominio da aplicação.</p>
   <p>Cada dóminio possui as pastas entity, que diz respeito a sua Entita ( Classe ); <br/><br/>
      Pasta repositories seria o local onde haveria a conexão com banco de dados para manusear as informações que diz respeito a seu dominio
   <br/><br/>
      Pasta services diz respeito ao local onde ficam as principais regras de negocio da aplicação.
   </p>

<br/>
 <p>Padrões que tentei por em prática neste projeto:</p>
1. Single Responsibility ( Cada service possui 1 metodo execute.
<p>2. Inversion Of Dependency ( Cada interação com as informacoes salvas sao de um unico repositorio enviado do arquivo Main )</p>
<p>3. DDD ( Projeto separdo por camadas )</p>


}






##Como executar o projeto
### Entrar na pasta purchase_auth

1. Acessar o terminal na pasta e executar o seguinte comando e gerar o executavel
    ```
    mvn clean package
    ```

2. Após rodar esse comando vai ser criado um arquivo .jar na pasta target.
    ```
    purchase_auth-1.0-SNAPSHOT-jar-with-dependencies.kar
    ```
3. Coloque este arquivo executavel na mesma pasta do arquivo .json com os dados que deseja enviar
    ```
    purchase_auth/target/purchase_auth[...] .jar
    ```
4. Após ter os dois arquivos na mesma parta execute com o comando
    ```
    java -cp "NOME_DO_ARQUIVO_EXECUTAVEL".jar Main < "NOME_DO_ARQUIVO".json
    ```
5. Exemplo de execução em que os dois arquivos estao na mesma pasta
    ```
    java -cp purchase_auth-1.0-SNAPSHOT-jar-with-dependencies.jar Main < operations_file.json
    ```




<p>Caso tenha alguma dúvida ou queira entrar em contato através do meu email "farciomernandes@gmail.com" ou whatsapp "(83)81613615"</p>