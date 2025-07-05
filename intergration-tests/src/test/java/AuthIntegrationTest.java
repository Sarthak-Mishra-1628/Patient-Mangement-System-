import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {

    @BeforeAll
    static void setUp(){
        RestAssured.baseURI = "http://localhost:4004";
    }

    @Test
    public void shouldReturnOKWithValidToken() {
        /*
        1. arrange - do any set up, this test needs to work
        2. act - the code we write , login endpoint and getting a response
        3. assert - to assert that response has a valid token and ok status
         */

        String loginpayload = """
                {
                    "email":"testuser@test.com",
                    "password":"password123"
                }
                """;
        Response response = given()
                            .contentType("application/json")
                            .body(loginpayload)
                            .when()
                            .post("/auth/login")
                            .then()
                            .statusCode(200)
                            .body("token" , notNullValue())
                            .extract()
                            .response();

        System.out.println("Generated token" + response.jsonPath().getString("token"));
    }

    @Test
    public void shouldReturnUnauthorizedOnInvalidLogin() {

        String loginpayload = """
                {
                    "email":"invalid_user@test.com",
                    "password":"invalid_password"
                }
                """;

        given()
              .contentType("application/json")
              .body(loginpayload)
              .when()
              .post("/auth/login")
              .then()
              .statusCode(401);
    }
}