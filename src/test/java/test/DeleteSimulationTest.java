package test;

import org.junit.Test;

import static baseUrl.baseUrl.BASE_URI;
import static io.restassured.RestAssured.given;

public class DeleteSimulationTest {

    @Test // remove simulação pelo ID
    public void removeSimulationForId()  {

        given()
                .when()
                    .delete(BASE_URI + "simulacoes/12")
                .then()
                    .statusCode(200);
    }
    @Test // remove simulação por ID inexistente
    public void removeSimulationForIdNoRecord()  {

        given()
                .when()
                .delete(BASE_URI + "simulacoes/99")
                .then()
                .statusCode(200);
    }
}
