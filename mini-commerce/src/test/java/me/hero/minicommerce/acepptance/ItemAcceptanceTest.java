package me.hero.minicommerce.acepptance;

import static org.assertj.core.api.Assertions.*;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@DisplayName("상품 관리 기능")
public class ItemAcceptanceTest extends AcceptanceTest {


  /**
   * Feature : 상품 생성 기능
   *  When : 상품 생성 요청을 한다.
   *  Then : 상품이 생성 된다.
   */
  @Test
  @DisplayName("상품 생성 기능")
  void createItem() {
    //given
    Map<String, String> params = new HashMap<>();
    params.put("name", "닭볶음탕");
    params.put("price", "18000");

    //when
    ExtractableResponse<Response> response = RestAssured.given().log().all()
        .body(params)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .post("/items")
        .then().log().all()
        .extract();

    //then
    assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    assertThat(response.header("Location")).isNotBlank();
  }

  /**
   * Feature : 상품 생성 기능
   *  Given : 상품이 생성되어 있다.
   *  When : 상품 수정 요청을 한다.
   *  Then : 상품이 수정 된다.
   */
  @Test
  @DisplayName("상품 수정 기능")
  void modifyItem() {
    //given
    Map<String, String> params = new HashMap<>();
    params.put("name", "닭볶음탕");
    params.put("price", "18000");
    ExtractableResponse<Response> savedItem = RestAssured.given().log().all()
        .body(params)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .post("/items")
        .then().log().all()
        .extract();

    String updateLocation = savedItem.header("Location");
    Map<String, String> updateParams = new HashMap<>();
    updateParams.put("name", "닭볶음탕");
    updateParams.put("price", "20000");

    //when
    ExtractableResponse<Response> response = RestAssured.given().log().all()
        .body(updateParams)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .put(updateLocation)
        .then().log().all()
        .extract();

    //then
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.jsonPath().getString("name")).isEqualTo(updateParams.get("name"));
    assertThat(response.jsonPath().getLong("price")).isEqualTo(updateParams.get("price"));
  }

  /**
   * Feature : 상품 삭제 기능
   *  Given : 상품이 생성되어 있다.
   *  When : 상품 삭제 요청을 한다.
   *  Then : 상품이 삭제 된다.
   */
  @Test
  @DisplayName("상품 삭제 기능")
  void deleteItem() {

  }

  /**
   * Feature : 상품 삭제 기능
   *  Given : 상품이 생성되어 있다.
   *  When : 생성된 상품 조회 요청을 한다.
   *  Then : 상품이 조회 된다.
   */
  @Test
  @DisplayName("상품 단건 조회 기능")
  void getOneItem() {

  }


  /**
   * Feature : 상품 삭제 기능
   *  Given : 상품 N개가 생성되어 있다.
   *  When : 생성된 상품 들을 조회 요청을 한다.
   *  Then : 상품들이 조회 된다.
   */
  @Test
  @DisplayName("전체 상품 조회 기능")
  void getAllItems() {

  }

}
