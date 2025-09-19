import model.Produto;
import repository.ProdutoRepository;
import service.ProdutoService;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        ProdutoRepository repo = new ProdutoRepository();
        ProdutoService service = new ProdutoService(repo);

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU ESTOQUE =====");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Adicionar estoque");
            System.out.println("4 - Remover produto");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

             try {
                switch (opcao) {
                    case 1:
                        cadastrarProduto(service, scanner);
                        break;
                    case 2:
                        listarProdutos(service);
                        break;
                    case 3:
                        adicionarEstoque(service, scanner);
                        break;
                    case 4:
                        removerProduto(service, scanner);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                scanner.nextLine(); 
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void cadastrarProduto(ProdutoService service, Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String desc = scanner.nextLine();
        System.out.print("Quantidade inicial: ");
        int qtd = scanner.nextInt();
        
        Produto p = service.cadastrarProduto(nome, desc, qtd);
        System.out.println("Produto cadastrado: " + p);
    }

    private static void listarProdutos(ProdutoService service) {
        List<Produto> lista = service.listarProdutos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("\n=== LISTA DE PRODUTOS ===");
            for (Produto produto : lista) {
                System.out.println(produto);
            }
        }
    }

    private static void adicionarEstoque(ProdutoService service, Scanner scanner) {
        System.out.print("ID do produto: ");
        int id = scanner.nextInt();
        System.out.print("Quantidade a adicionar: ");
        int qtdAdd = scanner.nextInt();
        
        Produto atualizado = service.adicionarEstoque(id, qtdAdd);
        System.out.println("Estoque atualizado: " + atualizado);
    }

    private static void removerProduto(ProdutoService service, Scanner scanner) {
        System.out.print("ID do produto: ");
        int id = scanner.nextInt();
        
        boolean removido = service.removerProduto(id);
        if (removido) {
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}