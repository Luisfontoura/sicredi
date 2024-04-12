package test;

import org.junit.Test;

import static baseUrl.baseUrl.BASE_URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;

public class ConsultaSimulacaoTest {

    @Test //Consulta simulação
    public void consultSimulation()  {

        given()
                .when()
                    .get(BASE_URI + "simulacoes/")
                .then()
                    .statusCode(200)
                .body("id", notNullValue());
    }

    @Test //Consulta simulação inexistente
    public void consultSimulationNoRecord()  {

        given()
                .when()
                .get(BASE_URI + "simulacoes/46331172033")
                .then()
                .statusCode(404)
                .body("mensagem", equalTo("CPF 46331172033 não encontrado"));
    }
}
