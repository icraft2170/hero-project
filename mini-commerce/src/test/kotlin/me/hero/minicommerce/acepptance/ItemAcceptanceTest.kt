package me.hero.minicommerce.acepptance

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

@DisplayName("상품 관리 기능")
class ItemAcceptanceTest : AcceptanceTest() {
    /**
     * Feature : 상품 생성 기능
     * When : 상품 생성 요청을 한다.
     * Then : 상품이 생성 된다.
     */
    @Test
    @DisplayName("상품 생성 기능")
    fun createItem() {
        //given
        val params = ItemSteps.상품_매개변수_생성("닭볶음탕", "18000")

        //when
        val response = ItemSteps.상품_생성(params)

        //then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
        Assertions.assertThat(response.header("Location")).isNotBlank
    }



    /**
     * Feature : 상품 수정 기능
     * Given : 상품이 생성되어 있다.
     * When : 상품 수정 요청을 한다.
     * Then : 상품이 수정 된다.
     */
    @Test
    @DisplayName("상품 수정 기능")
    fun modifyItem() {
        //given
        val params = ItemSteps.상품_매개변수_생성("닭볶음탕", "18000")
        val savedItem = ItemSteps.상품_생성(params)
        val updateLocation = savedItem.header("Location")
        val updateParams = ItemSteps.상품_매개변수_생성("닭볶음탕", "20000")

        //when
        val response = ItemSteps.상품_수정(updateLocation, updateParams)

        //then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(
            updateParams["name"]
        )
        Assertions.assertThat(response.jsonPath().getLong("price")).isEqualTo(
            updateParams["price"]!!.toLong()
        )
    }

    /**
     * Feature : 상품 삭제 기능
     * Given : 상품이 생성되어 있다.
     * When : 상품 삭제 요청을 한다.
     * Then : 상품이 삭제 된다.
     */
    @Test
    @DisplayName("상품 삭제 기능")
    fun deleteItem() {
        //given
        val params = ItemSteps.상품_매개변수_생성("닭볶음탕", "18000")
        val savedItem = ItemSteps.상품_생성(params)
        val deleteLocation = savedItem.header("Location")
        //when
        val response = ItemSteps.상품_삭제(deleteLocation)

        //then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())

        //when
        val getResponse = ItemSteps.상품_조회(deleteLocation)

        //then
        Assertions.assertThat(getResponse.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value())
    }


    /**
     * Feature : 상품 삭제 기능
     * Given : 상품이 생성되어 있다.
     * When : 생성된 상품 조회 요청을 한다.
     * Then : 상품이 조회 된다.
     */
    @DisplayName("상품 단건 조회 기능")
    @Test
    fun oneItem() {
        //given
        val params = ItemSteps.상품_매개변수_생성("닭볶음탕", "18000")
        val savedItem = ItemSteps.상품_생성(params)
        val deleteLocation = savedItem.header("Location")
        //when
        val response = ItemSteps.상품_조회(deleteLocation)

        //then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        Assertions.assertThat(response.jsonPath().getString("name")).isEqualTo(
            params["name"]
        )
        Assertions.assertThat(response.jsonPath().getLong("price")).isEqualTo(
            params["price"]!!.toLong()
        )
    }
}