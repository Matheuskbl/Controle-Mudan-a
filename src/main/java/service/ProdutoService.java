package service;

import model.Produto;
import repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;

public class ProdutoService {
    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto cadastrarProduto(String nome, String descricao, int quantidade) {
        
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa");
        }

       
        if (repository.existePorNome(nome)) {
            throw new IllegalArgumentException("Já existe um produto com este nome");
        }

        
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio");
        }

        Produto produto = new Produto(0, nome.trim(), descricao, quantidade);
        return repository.salvar(produto);
    }

    public List<Produto> listarProdutos() {
        return repository.buscarTodos();
    }

    public Produto adicionarEstoque(int id, int quantidade) {
       
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade a adicionar deve ser positiva");
        }

        Optional<Produto> produtoOpt = repository.buscarPorId(id);
        if (produtoOpt.isEmpty()) {
            throw new IllegalArgumentException("Produto não encontrado com ID: " + id);
        }

        Produto produto = produtoOpt.get();
        produto.setQuantidade(produto.getQuantidade() + quantidade);
        
        return repository.salvar(produto);
    }

    public boolean removerProduto(int id) {
        return repository.remover(id);
    }

    public Optional<Produto> buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public ProdutoRepository getRepository() {
        return repository;
    }
}