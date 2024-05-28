package dataFactory;


import pojo.ProdutoPojo;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoValido(){
        ProdutoPojo produto;
        produto = new ProdutoPojo();
        produto.setTitle("produto hildo");
        produto.setBrand("roja parfum");
        produto.setCategory("perfume");
        produto.setDescription("perfume teste");
        produto.setDiscountPercentage("15");
        produto.setStock("3");
        produto.setThumbnail("https://i.dummyjson.com/data/products/11/thumnail.jpg");

        return produto;
    }


    /*
    public static PostagemPojo criarPostagemDadosFormatoInvalido(){
        PostagemPojo postagem;
        postagem = new PostagemPojo();
        postagem.setBody("teste  xpto");
        postagem.setId(444);
        postagem.setUserId(123);

        return postagem;
    }

     */
}
