package repository;

import model.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepository {
    private List<Produto> produtos;
    private int nextId;

    public ProdutoRepository() {
        this.produtos = new ArrayList<>();
        this.nextId = 1;
    }

    public Produto salvar(Produto produto) {
        if (produto.getId() == 0) {
            produto = new Produto(nextId++, produto.getNome(), produto.getDescricao(), produto.getQuantidade());
        }
        produtos.add(produto);
        return produto;
    }

    public List<Produto> buscarTodos() {
        return new ArrayList<>(produtos);
    }

    public Optional<Produto> buscarPorId(int id) {
        return produtos.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public Optional<Produto> buscarPorNome(String nome) {
        return produtos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public boolean remover(int id) {
        return produtos.removeIf(p -> p.getId() == id);
    }

    public boolean existePorNome(String nome) {
        return produtos.stream()
                .anyMatch(p -> p.getNome().equalsIgnoreCase(nome));
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    public int getNextId() {
        return nextId;
    }
}