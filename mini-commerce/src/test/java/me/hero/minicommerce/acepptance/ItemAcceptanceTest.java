package me.hero.minicommerce.acepptance;

import static me.hero.minicommerce.acepptance.ItemSteps.상품_매개변수_생성;
import static me.hero.minicommerce.acepptance.ItemSteps.상품_삭제;
import static me.hero.minicommerce.acepptance.ItemSteps.상품_생성;
import static me.hero.minicommerce.acepptance.ItemSteps.상품_수정;
import static me.hero.minicommerce.acepptance.ItemSteps.상품_조회;
import static org.assertj.core.api.Assertions.*;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

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
    Map<String, String> params = 상품_매개변수_생성("닭볶음탕", "18000");

    //when
    ExtractableResponse<Response> response = 상품_생성(params);

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
    Map<String, String> params = 상품_매개변수_생성("닭볶음탕", "18000");
    ExtractableResponse<Response> savedItem = 상품_생성(params);

    String updateLocation = savedItem.header("Location");
    Map<String, String> updateParams = 상품_매개변수_생성("닭볶음탕", "20000");

    //when
    ExtractableResponse<Response> response = 상품_수정(updateLocation, updateParams);

    //then
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.jsonPath().getString("name")).isEqualTo(updateParams.get("name"));
    assertThat(response.jsonPath().getLong("price")).isEqualTo(Long.parseLong(updateParams.get("price")));
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
      //given
      Map<String, String> params = 상품_매개변수_생성("닭볶음탕", "18000");
      ExtractableResponse<Response> savedItem = 상품_생성(params);

      String deleteLocation = savedItem.header("Location");
      //when
      ExtractableResponse<Response> response = 상품_삭제(deleteLocation);

      //then
      assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);

      //when
      ExtractableResponse<Response> getResponse = 상품_조회(deleteLocation);

      //then
      assertThat(getResponse.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
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
    //given
    Map<String, String> params = 상품_매개변수_생성("닭볶음탕", "18000");
    ExtractableResponse<Response> savedItem = 상품_생성(params);

    String deleteLocation = savedItem.header("Location");
    //when
    ExtractableResponse<Response> response = 상품_조회(deleteLocation);

    //then
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.jsonPath().getString("name")).isEqualTo(params.get("name"));
    assertThat(response.jsonPath().getLong("price")).isEqualTo(Long.parseLong(params.get("price")));
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
