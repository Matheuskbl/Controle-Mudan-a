package tests;

import model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ProdutoRepository;
import service.ProdutoService;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoServiceTest {
    private ProdutoService service;
    private ProdutoRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ProdutoRepository();
        service = new ProdutoService(repository);
    }

    @Test
    void testCadastrarProdutoComSucesso() {
        
        Produto produto = service.cadastrarProduto("Notebook", "Dell i7", 10);

        assertNotNull(produto);
        assertEquals(1, produto.getId());
        assertEquals("Notebook", produto.getNome());
        assertEquals("Dell i7", produto.getDescricao());
        assertEquals(10, produto.getQuantidade());
    }

    @Test
    void testCadastrarProdutoComQuantidadeNegativa() {
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrarProduto("Produto Teste", "Descrição", -5);
        });

        assertEquals("A quantidade não pode ser negativa", exception.getMessage());
    }

    @Test
    void testCadastrarProdutoComNomeDuplicado() {
        
        service.cadastrarProduto("Produto A", "Descrição A", 5);

        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrarProduto("Produto A", "Descrição B", 10);
        });

        assertEquals("Já existe um produto com este nome", exception.getMessage());
    }

    @Test
    void testCadastrarProdutoComNomeVazio() {
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrarProduto("", "Descrição", 5);
        });

        assertEquals("O nome do produto não pode ser vazio", exception.getMessage());
    }

    @Test
    void testListarProdutosVazio() {
        
        var produtos = service.listarProdutos();

     
        assertTrue(produtos.isEmpty());
    }

    @Test
    void testListarProdutosComItens() {
        
        service.cadastrarProduto("Produto A", "Desc A", 5);
        service.cadastrarProduto("Produto B", "Desc B", 10);

       
        var produtos = service.listarProdutos();

      
        assertEquals(2, produtos.size());
    }

    @Test
    void testAdicionarEstoqueComSucesso() {
      
        Produto produto = service.cadastrarProduto("Produto Teste", "Descrição", 5);

        Produto atualizado = service.adicionarEstoque(produto.getId(), 10);

        
        assertEquals(15, atualizado.getQuantidade());
    }

    @Test
    void testAdicionarEstoqueQuantidadeInvalida() {
        
        Produto produto = service.cadastrarProduto("Produto Teste", "Descrição", 5);

       
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.adicionarEstoque(produto.getId(), 0);
        });

        assertEquals("A quantidade a adicionar deve ser positiva", exception.getMessage());
    }

    @Test
    void testAdicionarEstoqueProdutoNaoEncontrado() {
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.adicionarEstoque(999, 10);
        });

        assertEquals("Produto não encontrado com ID: 999", exception.getMessage());
    }

    @Test
    void testRemoverProdutoComSucesso() {
        
        Produto produto = service.cadastrarProduto("Produto Teste", "Descrição", 5);

       
        boolean removido = service.removerProduto(produto.getId());

     
        assertTrue(removido);
        assertTrue(service.listarProdutos().isEmpty());
    }

    @Test
    void testRemoverProdutoNaoEncontrado() {
       
        boolean removido = service.removerProduto(999);

      
        assertFalse(removido);
    }
}