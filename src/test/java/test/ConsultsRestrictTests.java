package test;

import static baseUrl.baseUrl.BASE_URI;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "ConsultsRestrictTestsData.csv")
public class ConsultsRestrictTests {

    @Test //Consulta CPF com restrição
    public void CpfComRestrictTests(@Param(name = "cpf")String cpf) {
        given()
                .when()
                    .get(BASE_URI + "restricoes/" + cpf)
                .then()
                    .statusCode(200)
                    .body("mensagem", equalTo("O CPF " + cpf + " tem problema"));
    }

    @Test //Consulta CPF sem restrição
    public void CpfSemRestrictTests(@Param(name = "cpf")String cpf) {
        given()
                .when()
                    .get(BASE_URI + "restricoes/" + cpf)
                .then()
                    .statusCode(204);
    }
}