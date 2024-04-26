package org.example;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateUserTest {
    private static final String BASE_URI = "http://reqres.in/api/";
    private static final String CREATE_USER_ENDPOINT = "users";

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

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }
    }

    @BeforeClass
    public void setup() {
        RestAssured.filters(new RequestLoggingFilter(LogDetail.ALL), new ResponseLoggingFilter(LogDetail.ALL));
    }

    @Test
    public void testCreateUser() {
        RestAssured.baseURI = BASE_URI;

        User user = new User("John Doea", "QA Engineer");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(CREATE_USER_ENDPOINT)
                .then()
                .extract()
                .response();
        assertEquals(response.getStatusCode(), 301);

    }
}