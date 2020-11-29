import java.util.*;

public class Carrinho {

    double Total = 0;
    int Items = 0;

    Carrinho(){
        Produtos = new ArrayList<Produto>();
        Total = 0;
    }

    List<Produto> Produtos;

    public void RemoverProduto(Produto produto) {
        Produtos.remove(produto);
        Total -= produto.Valor;
    }

    public void AdicionarProduto(Produto produto){

        if(Produtos.stream().anyMatch(p -> p.Id == produto.Id)){
            Total += produto.Quantidade * produto.Valor;

            Produto produtoExistente = Produtos.stream().filter(p -> p.Id == produto.Id).findAny().orElse(null);

            Produtos.remove(produtoExistente);
            produtoExistente.Quantidade = produtoExistente.Quantidade + produto.Quantidade;

            Produtos.add(produtoExistente);
        }else{
            Produtos.add(produto);
            Total += produto.Valor;
        }
    }

    public boolean LimparCarrinho(){
        Total = 0;
        return Produtos.removeAll(Produtos);
    }
}
