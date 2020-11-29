public class Produto {
    Produto(int id,String nome,double valor,int quantidade) throws Exception {
        if(!ValidarEstoque(id,quantidade))
            throw new Exception("Quantidade inválida");

        Id = id;
        Nome = nome;
        Valor = valor;
        Quantidade = quantidade;
    }

    public int Id;
    public String Nome;
    public double Valor;
    public int Quantidade;

    public boolean ValidarEstoque(int id,int quantidade){

        int estoque = QuantidadeEstoque(id);

        if(quantidade > estoque)
            return false;

        return true;
    }

    public int QuantidadeEstoque(int id){
        return 15;
    }


}

