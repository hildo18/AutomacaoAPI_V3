package modulos.operacoes;

import dataFactory.ProdutoDataFactory;

import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;


@DisplayName("Testes de API do modulo de Postagem")
public class OperacoesTest {

    private String token;


    @BeforeEach
    public void beforeEach(){


        //Configurando dados da api
        baseURI = "https://dummyjson.com";
        //basePath = "/posts";

        this.token = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"username\": \"kminchelle\",\n" +
                        "    \"password\": \"0lelplR\"\n" +
                        "}")
                .when()
                .post("/auth/login")
                .then()
                .extract()
                .path("token");
    }

    @Test
    @DisplayName("Validar status da aplicação")
    public void testValidarStatusAplicacao(){
        given()
                .contentType(ContentType.JSON);
        when()
                .get()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Validar status da aplicação url inválida")
    public void testValidarStatusAplicacaoURLInvalida(){
        given()
                .contentType(ContentType.JSON);
        when()
                .get("http://dummyjson.com/testt")
                .then()
                .assertThat()
                .statusCode(404);
    }

    @Test
    @DisplayName("Validar retorno de  usuários")
    public void testValidarRetornoUsers(){
        given()
                .contentType(ContentType.JSON);
        when()
                .get("/users")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Validar retorno de produtos com autenticação")
    public void testValidarRetornoProdAutenticacao(){

        given()
                .header("token", "Bearer " + this.token)
                .contentType(ContentType.JSON);

        when()
                .get("/auth/products")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Validar retorno de produtos com token ausente")
    public void testValidarRetornoProdTokenAusente(){
        given()
                .contentType(ContentType.JSON);

        when()
                .get("/auth/products")
                .then()
                .assertThat()
                .statusCode(403);
    }


    @Test
    @DisplayName("Validar se o produto foi adicionado")
    public void testValidarProdutoAdicionado(){
        given()
                .contentType(ContentType.JSON)
                .body(ProdutoDataFactory.criarProdutoValido())
                .when()
                .post("/products/add")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Validar adicionao de produto com json inválido")
    public void testValidarProdutoAdicionadoJsonInvalido(){
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    title: \"jason invalido\",\n" +
                        "  \n" +
                        "}")
                .when()
                .post("/products/add")
                .then()
                .assertThat()
                .statusCode(400);
    }


    @Test
    @DisplayName("Validar retorno de produtos")
    public void testValidarRetornoProdutos(){
        given()
                .contentType(ContentType.JSON);
        when()
                .get("/products")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Validar retorno de produtos por id")
    public void testValidarBuscarProdutosID(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products/1")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Validar retorno de produtos por id inexistente")
    public void testValidarBuscarProdutosIDInexistente(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products/xx")
                .then()
                .assertThat()
                .statusCode(404);
    }


}
