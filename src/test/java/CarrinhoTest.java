import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.testng.Assert;

public class CarrinhoTest {

    Produto produto1 = new Produto(1,"Notebook",3000,1);
    Produto produto2 = new Produto(2, "Monitor",1500,1);
    Produto produto3 = new Produto(1, "Notebook",3000,2);
    Produto produto4 = new Produto(1, "Notebook",3000,14);

    public CarrinhoTest() throws Exception {
    }


    @Before
    public void PreparaTest() throws Exception {

    }

    @Test
    public void adicionarProdutoNoCarrinhoTest(){
        //arrange

        //act
        Carrinho carrinho = new Carrinho();
        carrinho.AdicionarProduto(produto1);

        //assert
        Assert.assertEquals(carrinho.Produtos.size(),1);
        Assert.assertEquals(carrinho.Produtos.get(0).Nome,"Notebook");
        Assert.assertEquals(carrinho.Produtos.get(0).Valor,3000.0);

    }

    @Test
    public void removerProdutoNoCarrinhoTest(){
        //arrange

        Carrinho carrinho = new Carrinho();
        carrinho.AdicionarProduto(produto1);

        //act
        carrinho.RemoverProduto(produto1);

        //assert
        Assert.assertEquals(carrinho.Produtos.size(),0);
    }

    @Test
    public void limparCarrinhoTest(){
        //arrange
        Carrinho carrinho = new Carrinho();
        carrinho.AdicionarProduto(produto1);

        //act
        boolean status = carrinho.LimparCarrinho();

        //assert
        Assert.assertEquals(status,true);
    }

    @Test
    public void adicionarProdutoESomarValorCarrinhoTest(){
        //arrange

        //act
        Carrinho carrinho = new Carrinho();
        carrinho.AdicionarProduto(produto1);
        carrinho.AdicionarProduto(produto2);

        //assert
        Assert.assertEquals(carrinho.Total,4500.0);
    }

    @Test
    public void removerProdutoESubtrairValorCarrinhoTest(){
        //arrange
        Carrinho carrinho = new Carrinho();
        carrinho.AdicionarProduto(produto1);
        carrinho.AdicionarProduto(produto2);

        //act
        carrinho.RemoverProduto(produto1);

        //assert
        Assert.assertEquals(carrinho.Total,1500.0);
    }

    @Test
    public void adicionarProdutoRepetidoTest(){
        //arrange
        Carrinho carrinho = new Carrinho();

        //act
        carrinho.AdicionarProduto(produto1);
        carrinho.AdicionarProduto(produto1);

        //assert
        Assert.assertEquals(carrinho.Produtos.size(),1);
        Assert.assertEquals(carrinho.Total,6000.0);
        Assert.assertEquals(2, carrinho.Produtos.get(carrinho.Produtos.indexOf(produto1)).Quantidade);
    }

    @Test
    public void adicionarProdutoJaExistenteTest(){
        //arrange
        Carrinho carrinho = new Carrinho();

        //act
        carrinho.AdicionarProduto(produto1);
        carrinho.AdicionarProduto(produto2);
        carrinho.AdicionarProduto(produto3);

        //assert
        Assert.assertEquals(carrinho.Produtos.size(),2);
        Assert.assertEquals(carrinho.Total,10500.0);
        Assert.assertEquals(3, carrinho.Produtos.get(carrinho.Produtos.indexOf(produto1)).Quantidade);

    }

    @Test
    public void adicionarProdutoComEstoqueValidoTest(){
        //arrange
        Carrinho carrinho = new Carrinho();

        //act
        carrinho.Produtos.add(produto3);

        //assert
        Assert.assertEquals(carrinho.Produtos.size(),1);

    }

    @Test
    public void adicionarProdutoComEstoqueInvalidoTest() throws Exception{
        //arrange
        Carrinho carrinho = new Carrinho();

        Produto spy = PowerMockito.spy(produto4);
        PowerMockito.when(spy.QuantidadeEstoque(20)).thenReturn((int)10);

        //act
        boolean temEstoque = spy.ValidarEstoque(1,20);


        //assert
        Assert.assertFalse(temEstoque);

    }
}
