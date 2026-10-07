package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import model.User;

import static io.restassured.RestAssured.given;

public class UserApiClient {

    private static final String BASE_URL =
            System.getProperty("test.baseUrl", "https://qa-desk.education-services.ru")
                    .replaceAll("/+$", "");

    private static final String SIGN_UP_PATH = "/api/signup";

    @Step("Создать тестового пользователя через API")
    public void createUser(User user) {
        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(SIGN_UP_PATH)
                .then()
                .statusCode(201);
    }
}