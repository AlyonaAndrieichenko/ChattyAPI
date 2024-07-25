package apiUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static apiUtil.UrlUtil.BASE_URL;
import static apiUtil.UrlUtil.specification;
import static io.restassured.RestAssured.given;

public class ApiRequests {

    private ApiRequests() {
    }

    public static Response postRequestNoToken(String endpoint, Object body, int statusCode) {
        return given()
                .spec(specification)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    public static Response postRequest(String endpoint, Object body, int statusCode, String accessToken) {
        return given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessToken)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    public static Response postRequestFormData(String endpoint, File img, int statusCode, String accessToken) {
        return given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType("multipart/form-data")
                .multiPart("multipartFile", img, "image/png")
                .log().all()
                .when()
                .post(BASE_URL + endpoint)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    public static Response getRequest(String endpoint, int statusCode, String accessToken) {
        return given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    public static Response putRequest(String endpoint, Object updatedBody, int statusCode, String accessToken) {
        return given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessToken)
                .body(updatedBody)
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    public static Response deleteRequest(String endpoint, int statusCode, String accessToken, String idUser) {
        return given()
                .spec(specification)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(endpoint + idUser)
                .then()
                .log().all()
                .statusCode(statusCode)
                .extract()
                .response();
    }
}
