package test;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static baseUrl.baseUrl.BASE_URI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;

public class CriarSimulacaoTest {

    @Test // Criando simulação para validar status 201
    public void CreateSimulationTest() throws IOException {

        String requestBody = new String(Files.readAllBytes(Paths.get("src/main/resources/JSON/dados.json")));

        given()
                    .contentType("application/json")
                    .body(requestBody)
                .when()
                    .post(BASE_URI + "simulacoes")
                .then()
                    .statusCode(201)
                    .body("id", is(notNullValue()))
                    .body("nome", is("Pacheco"))
                    .body("cpf", is("96093236150"))
                    .body("email", is("email@email.com"))
                    .body("valor", is(1209))
                    .body("parcelas",is(2))
                    .body("seguro", is(true));
    }

    @Test // Criando simulação com CPF duplicado para receber status 400
    public void CreateSimulationWithCpfDuplicatedTest() throws IOException {
        // Lendo o conteúdo do arquivo JSON
        String requestBody = new String(Files.readAllBytes(Paths.get("src/main/resources/JSON/dadosDuplicados.json")));

        given()
                    .contentType("application/json")
                    .body(requestBody)
                .when()
                    .post(BASE_URI + "simulacoes")
                .then()
                    .statusCode(400)
                    .body("mensagem", is("CPF duplicado"));
    }
    @Test // Criando uma simulação sem campos obrigatórios
    public void CreateSimulationWithFieldSecureTest() throws IOException {

        String requestBody = new String(Files.readAllBytes(Paths.get("src/main/resources/JSON/campoSeguro.json")));

        given()
                    .contentType("application/json")
                    .body(requestBody)
                .when()
                    .post(BASE_URI + "simulacoes")
                .then()
                    .statusCode(400)
                    .body("erros.parcelas", equalTo("Parcelas não pode ser vazio"))
                    .body("erros.cpf", equalTo("CPF não pode ser vazio"))
                    .body("erros.valor", equalTo("Valor não pode ser vazio"))
                    .body("erros.nome", equalTo("Nome não pode ser vazio"))
                    .body("erros.email", equalTo("E-mail não deve ser vazio"));
    }

    @Test // Criando simulação com atributos valor e parcela inválidos
    public void ValidateSimulationWithValueIncorrectTest() throws IOException {
        // Lendo o conteúdo do arquivo JSON
        String requestBody = new String(Files.readAllBytes(Paths.get("src/main/resources/JSON/valorSimulacao1.json")));

        given()
                    .contentType("application/json")
                    .body(requestBody)
                .when()
                    .post(BASE_URI + "simulacoes")
                .then()
                    .statusCode(400)
                    .body("erros.parcelas", equalTo("Parcelas deve ser igual ou maior que 2"));
    }
    @Test // Criando simulação com atributos valor e parcela válidos
    public void ValidateSimulationWithValueCorrectTest() throws IOException {

        String requestBody = new String(Files.readAllBytes(Paths.get("src/main/resources/JSON/valorSimulacao2.json")));

        given()
                    .contentType("application/json")
                    .body(requestBody)
                .when()
                    .post(BASE_URI + "simulacoes")
                .then()
                    .statusCode(201)
                    .body("parcelas", equalTo(2))
                    .body("cpf", equalTo("98648273081"))
                    .body("valor", equalTo(1000))
                    .body("nome", equalTo("Lucas"))
                    .body("email", equalTo("email@email.com"));
    }

    @Test // Criando simulação com atributos valor e parcela válidos
    public void ValidateSimulationWithSucessTest() throws IOException {
        // Lendo o conteúdo do arquivo JSON
        String requestBody = new String(Files.readAllBytes(Paths.get("src/main/resources/JSON/valorSimulacao3.json")));

        given()
                    .contentType("application/json")
                    .body(requestBody)
                .when()
                    .post(BASE_URI + "simulacoes")
                .then()
                    .statusCode(201)
                    .body("parcelas", equalTo(10))
                    .body("cpf", equalTo("98648273082"))
                    .body("valor", equalTo(10000))
                    .body("nome", equalTo("Andre"))
                    .body("email", equalTo("email@email.com"));
    }

    @Test // Criando simulação com atributos valor e parcela válidos
    public void ValidateSimulationWithLimitValueTest() throws IOException {
        // Lendo o conteúdo do arquivo JSON
        String requestBody = new String(Files.readAllBytes(Paths.get("src/main/resources/JSON/valorSimulacao4.json")));

        given()
                    .contentType("application/json")
                    .body(requestBody)
                .when()
                    .post(BASE_URI + "simulacoes")
                .then()
                    .statusCode(201)
                    .body("parcelas", equalTo(48))
                    .body("cpf", equalTo("98648273083"))
                    .body("valor", equalTo(40000))
                    .body("nome", equalTo("Juan"))
                    .body("email", equalTo("email@email.com"));
    }

    @Test // Criando simulação com atributos valor e parcela inválidos
    public void ValidateSimulationWithValueFailureTest() throws IOException {
        // Lendo o conteúdo do arquivo JSON
        String requestBody = new String(Files.readAllBytes(Paths.get("src/main/resources/JSON/valorSimulacao5.json")));

        given()
                    .contentType("application/json")
                    .body(requestBody)
                .when()
                    .post(BASE_URI + "simulacoes")
                .then()
                    .statusCode(400)
                    .body("erros.valor", equalTo("Valor deve ser menor ou igual a R$ 40.000"));
    }
}