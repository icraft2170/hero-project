package me.hero.minicommerce.acepptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;

public class ItemSteps {
  public static ExtractableResponse<Response> 상품_생성(Map<String, String> params) {
    ExtractableResponse<Response> response = RestAssured.given().log().all()
        .body(params)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .post("/items")
        .then().log().all()
        .extract();
    return response;
  }

  public static Map<String, String> 상품_매개변수_생성(String name, String price) {
    Map<String, String> params = new HashMap<>();
    params.put("name", name);
    params.put("price", price);
    return params;
  }

  public static ExtractableResponse<Response> 상품_수정(String updateLocation,
      Map<String, String> updateParams) {
    ExtractableResponse<Response> response = RestAssured.given().log().all()
        .body(updateParams)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .put(updateLocation)
        .then().log().all()
        .extract();
    return response;
  }

  public static ExtractableResponse<Response> 상품_삭제(String deleteLocation) {
    return RestAssured.given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .delete(deleteLocation)
        .then().log().all()
        .extract();
  }




}
