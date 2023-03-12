package clients;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseClient {
    public static String BASE_URL = "https://swapi.dev/api/";
    private Map<Integer, String> responseCodeAndBody = new HashMap<>();

    public RequestSpecification addSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public ResponseSpecification addResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL)
                .build();
    }

    public Map<Integer, String> get(String url) {
        Response response = RestAssured.given()
                .spec(addSpec())
                .when()
                .get(url);
        String body = response.then().extract().body().toString();
        Integer responseCode = response.statusCode();
        responseCodeAndBody.put(responseCode, body);
        return responseCodeAndBody;
    }

    public Response getWithParams(String url, String paramName, int value) {
        return RestAssured.given()
                .spec(addSpec())
                .pathParam(paramName, value)
                .when()
                .get(url);
    }

    public Response getWithQueryParams(String url, String paramName, int value) {
        return RestAssured.given()
                .spec(addSpec())
                .and()
                .param(paramName, value)
                .when()
                .get()
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public Response post(String url, String body) {
        return RestAssured.given()
                .spec(addSpec())
                .and()
                .body(body)
                .when()
                .post(url)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }
}
