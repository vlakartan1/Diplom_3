package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateAndRemoveUser {
    public static final String CONTENT_TYPE = "Content-type";
    public static final String APPLICATION = "application/json";
    public static final String USER_LOGIN_API = "https://stellarburgers.nomoreparties.site/api/auth/login";
    public static final String USER_API = "https://stellarburgers.nomoreparties.site/api/auth/register";
    public static final String REMOVE_USER_API = "https://stellarburgers.nomoreparties.site/api/auth/user/";
    public static final String NAME = "Karoline";
    public static final String EMAIL = "karoline@yandex.ru";
    public static final String PASSWORD = "121212";
    public static final CreateAndRemoveUser NEW_USER = new CreateAndRemoveUser(EMAIL, PASSWORD, NAME);
    public static final CreateAndRemoveUser AUTH_USER = new CreateAndRemoveUser(EMAIL, PASSWORD);
    private String email;
    private String password;
    private String name;

    public CreateAndRemoveUser(String email) {
        this.email = email;
    }

    public CreateAndRemoveUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public CreateAndRemoveUser(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public CreateAndRemoveUser() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Step("Cоздать уникального тестового пользователя")
    public void setNewUser() {

        Response response =
                given()
                        .header(CONTENT_TYPE, APPLICATION)
                        .body(NEW_USER)
                        .when()
                        .post(USER_API);
        response
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }


    @Step("Авторизоваться от созданного пользователя и получить его токен")
    public String authUserAndReceiveToken() {
        String userToken;
        Response response =
                given()
                        .header(CONTENT_TYPE, APPLICATION)
                        .body(AUTH_USER)
                        .when()
                        .post(USER_LOGIN_API);

        userToken = response
                .then()
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .extract()
                .path("accessToken").toString();

        return userToken;
    }

    @Step("Удалить пользователя")
    public void removeUserByToken() {
        String token = authUserAndReceiveToken();
        Response response =
                given()
                        .header(CONTENT_TYPE, APPLICATION)
                        .header("Authorization", token)
                        .when()
                        .delete(REMOVE_USER_API);
        response
                .then()
                .body("success", equalTo(true))
                .body("message", equalTo("User successfully removed"));
        System.out.println("Тестовый Пользователь успешно удален");

    }

}
