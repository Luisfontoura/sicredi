package test;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static baseUrl.baseUrl.BASE_URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ChangeSimulationTest {

    @Test //Alterando uma simulação inexistente
    public void changeSimulationNoRecordTest() throws IOException {

        String requestBody = new String(Files.readAllBytes(Paths.get("src/main/resources/JSON/changeSimulation1.json")));

        given()
                    .contentType("application/json")
                    .body(requestBody)
                .when()
                    .put(BASE_URI + "simulacoes/97093236014")
                .then()
                    .statusCode(404)
                    .body("mensagem", equalTo("CPF 97093236014 não encontrado"));
    }

    @Test // Alterando todos os atributos de uma simulação
    public void changeSimulationWithAllAttributesTest() throws IOException {

        String requestBody = new String(Files.readAllBytes(Paths.get("src/main/resources/JSON/changeSimulation2.json")));

        given()
                    .contentType("application/json")
                    .body(requestBody)
                .when()
                    .put(BASE_URI + "simulacoes/66414919004")
                .then()
                    .statusCode(200);
    }

}
