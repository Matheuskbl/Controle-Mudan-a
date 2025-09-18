import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Produto {
    private int codigo;
    private String nome;
    private int unidade;

    public Produto(int codigo, String nome, int unidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.unidade = unidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getUnidade() {
        return unidade;
    }

    public void adicionarUnidade(int quantidade) {
        this.unidade += quantidade;
    }

    @Override
    public String toString() {
        return String.format("Código: %d | Nome: %s | Unidades: %d", codigo, nome, unidade);
    }
}

public class CatalogoProdutos {
    private List<Produto> produtos;
    private Scanner scanner;

    public CatalogoProdutos() {
        produtos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void executar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    adicionarUnidade();
                    break;
                case 4:
                    buscarProduto();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }

    private void exibirMenu() {
        System.out.println("=== CATÁLOGO DE PRODUTOS ===");
        System.out.println("1. Inserir produto em posição");
        System.out.println("2. Listar produtos");
        System.out.println("3. Adicionar unidade a um produto");
        System.out.println("4. Buscar produto por código");
        System.out.println("0. Sair");
    }

    private void cadastrarProduto() {
        System.out.println("\n--- CADASTRAR PRODUTO ---");

        int codigo = lerOpcao("Digite código: ");
        int unidade = lerOpcao("Digite quantas unidades o produto tem: ");
        int posicao = lerOpcao("Digite posição: ");

        System.out.print("Digite nome: ");
        String nome = scanner.nextLine();

        if (posicao < 0 || posicao > produtos.size()) {
            System.out.println("Posição inválida! Deve estar entre 0 e " + produtos.size());
            return;
        }

        Produto novoProduto = new Produto(codigo, nome, unidade);
        produtos.add(posicao, novoProduto);
        System.out.println("Produto inserido com sucesso!");
    }

    private void listarProdutos() {
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (int i = 0; i < produtos.size(); i++) {
                System.out.println((i + 1) + ". " + produtos.get(i));
            }
        }
    }

    private void adicionarUnidade() {
        System.out.println("\n--- ADICIONAR UNIDADE ---");
        int codigo = lerOpcao("Digite o código do produto: ");
        Produto produto = buscarProdutoPorCodigo(codigo);

        if (produto != null) {
            int qtd = lerOpcao("Digite a quantidade a adicionar: ");
            produto.adicionarUnidade(qtd);
            System.out.println("Unidades adicionadas com sucesso!");
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    private void buscarProduto() {
        System.out.println("\n--- BUSCAR PRODUTO ---");
        int codigo = lerOpcao("Digite o código do produto: ");

        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            System.out.println("Produto encontrado:");
            System.out.println(produto);
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    private Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    private int lerOpcao(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número inteiro válido!");
            }
        }
    }

    public static void main(String[] args) {
        CatalogoProdutos catalogo = new CatalogoProdutos();
        catalogo.executar();
    }
}
