package me.hero.minicommerce.item.domain

import me.hero.minicommerce.item.adapter.out.persistance.dto.ItemModifyParams
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("상품 도메인 테스트")
internal class ItemTest {
    @Test
    @DisplayName("상품 수정 modify() 메소드 단위테스트")
    fun modifyItem() {
        //given
        val newItem = Item("닭도리탕", 18000L)
        val params = ItemModifyParams("닭도리탕", 20000L)

        //when
        newItem.modify(params)

        //then
        assertThat(newItem.name).isEqualTo(params.name)
        assertThat(newItem.price).isEqualTo(params.price)
    }
}