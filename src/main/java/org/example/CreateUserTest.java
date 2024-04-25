package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateUserTest {

    // URI base da API
    private static final String BASE_URI = "https://reqres.in/api/";

    // Endpoint para criar usuário
    private static final String CREATE_USER_ENDPOINT = "users";

    // Classe POJO para representar os dados do usuário
    public static class User {
        private String name;
        private String job;

        public User(String name, String job) {
            this.name = name;
            this.job = job;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        // Getters e Setters
    }

    @Test
    public void testCreateUser() {
        // Configuração base do RestAssured
        RestAssured.baseURI = BASE_URI;

        // Dados do usuário a serem criados
        User user = new User("John Doe", "QA Engineer");

        // Realiza a requisição POST para criar um novo usuário
        Response response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(CREATE_USER_ENDPOINT)
                .then()
                .extract()
                .response();

        // Validação do Status Code
//        assertEquals(response.getStatusCode(), 201, "Status code inválido");
        assertEquals(response.getStatusCode(), 201);

        // Validação dos campos obrigatórios (pode ser feito acessando os campos do JSON na resposta)

        // Validação do contrato (opcional, pode ser feito utilizando ferramentas como o JSON Schema Validator)
    }
}
