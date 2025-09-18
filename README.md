# Sistema de Controle de Estoque Simplificado (SCES)

Este projeto consiste em um Sistema de Controle de Estoque Simplificado desenvolvido em Java.  
O objetivo principal é aplicar práticas de desenvolvimento ágil, integração contínua e gerenciamento de mudanças.

O sistema permite:
- Cadastrar produtos no estoque.
- Listar produtos cadastrados.
- Adicionar unidades a produtos existentes.
- Buscar produtos pelo código.

## Estrutura do Projeto

O projeto segue a estrutura padrão de um projeto Maven:

catalogo-produtos/
├── pom.xml
└── src/
└── main/
└── java/
└── CatalogoProdutos.java


- `CatalogoProdutos.java` – Contém a implementação do sistema.
- `pom.xml` – Configuração do Maven para compilação e execução do projeto.

---

## Como Executar o Projeto Localmente

1. Certifique-se de ter Java 17 e Maven instalados em sua máquina.
2. Clone este repositório:
git clone https://github.com/Matheuskbl/Controle-Mudan-a.git

Entre na pasta do projeto:
cd catalogo-produtos

Compile o projeto:
mvn clean install

Execute a aplicação:
java -jar target/catalogo-produtos-1.0-SNAPSHOT.jar

Use o menu interativo no console para testar as funcionalidades.

Integração Contínua (GitHub Actions)
O repositório possui um workflow configurado em .github/workflows/maven.yml, que realiza as seguintes tarefas:

Compilação automática do projeto a cada push ou pull request na branch principal (main).

Execução de testes unitários para garantir que as funcionalidades não quebrem.

Exibição do status de sucesso ou falha diretamente no GitHub.

Gerência de Projeto (Taiga)
O projeto é gerenciado utilizando a ferramenta Taiga:

Link do projeto no Taiga: https://tree.taiga.io/project/matheuskbl-sistema-de-controle-de-estoque-simplificado-sces/backlog

Todas as funcionalidades estão cadastradas como User Stories, e tarefas técnicas foram adicionadas para garantir rastreabilidade.

Cada commit no GitHub referencia a User Story correspondente (exemplo: refs #1), mantendo a conexão entre código e gerenciamento.

Funcionalidades Implementadas
Cadastrar Produto

Cada produto recebe um ID único.

Não é permitido cadastrar produtos com nomes duplicados.

Quantidade inicial do produto deve ser igual ou maior que zero.

Listar Produtos

Exibe ID, nome e quantidade em estoque.

Caso não haja produtos cadastrados, é exibida uma mensagem informando.

Adicionar Unidades

Permite adicionar uma quantidade positiva de unidades a um produto existente.

Retorna mensagem de erro se o produto não for encontrado.

Buscar Produto por Código

Localiza o produto pelo ID e exibe suas informações.

Retorna mensagem caso o produto não exista.

Testes Unitários
Testes foram implementados para:

Validar cadastro de produtos.

Garantir que não haja duplicidade de nomes.

Testar adição de unidades.

Tratar casos de produtos não encontrados.

O workflow do GitHub Actions executa esses testes automaticamente a cada alteração no código.

Observações
O desenvolvimento seguiu o fluxo ágil:

Cada funcionalidade foi implementada em uma feature branch.

As alterações foram submetidas via Pull Request para a branch principal.

O projeto demonstra boas práticas de CI/CD, controle de versão e rastreabilidade entre código e tarefas de gerenciamento.
