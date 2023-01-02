package me.hero.minicommerce.acepptance

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.springframework.http.MediaType

object ItemSteps {
    fun 상품_생성(params: Map<String, String>): ExtractableResponse<Response> {
        return RestAssured.given().log().all()
            .body(params)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .`when`()
            .post("/items")
            .then().log().all()
            .extract()
    }

    fun 상품_매개변수_생성(name: String, price: String): Map<String, String> {
        val params: MutableMap<String, String> = HashMap()
        params["name"] = name
        params["price"] = price
        return params
    }

    fun 상품_수정(
        updateLocation: String?,
        updateParams: Map<String, String>
    ): ExtractableResponse<Response> {
        return RestAssured.given().log().all()
            .body(updateParams)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .`when`()
            .put(updateLocation)
            .then().log().all()
            .extract()
    }

    fun 상품_삭제(deleteLocation: String?): ExtractableResponse<Response> {
        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .`when`()
            .delete(deleteLocation)
            .then().log().all()
            .extract()
    }

    fun 상품_조회(deleteLocation: String?): ExtractableResponse<Response> {
        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .`when`()[deleteLocation]
            .then().log().all()
            .extract()
    }
}