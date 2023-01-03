package me.hero.minicommerce.item.adapter.out.persistance

import me.hero.minicommerce.item.application.port.`in`.dto.ModifyItemDto
import me.hero.minicommerce.item.domain.Item
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.anyVararg


@DisplayName("상품 영속성 어댑터 테스트")
@ExtendWith(MockitoExtension::class)
class ItemPersistenceAdapterTest {
    @InjectMocks lateinit var itemPersistenceAdapter: ItemPersistenceAdapter
    @Mock lateinit var itemRepository: ItemRepository

    @DisplayName("상품 조회 - 해당 값이 없는 케이스")
    @Test
    fun findOneItem_fail() {
        //when then
        assertThrows<IllegalArgumentException> {
            itemPersistenceAdapter.findOneItem(ITEM_ID)
        }.apply {
            assertThat(message).isEqualTo("해당 상품을 찾을 수 없습니다.")
        }
    }


    @Test
    @DisplayName("상품 수정 Adapter - 실패 케이스 ( Repository.find()가 null 일 때 )")
    fun failModifyItem() {
        //given
        val modifyDto = ModifyItemDto("닭볶음탕", 20000L)
        Mockito.`when`(
            itemRepository.findNullableById(anyVararg())
        ).thenReturn(null)

        //when then
        assertThrows<IllegalArgumentException> {
            itemPersistenceAdapter.modifyItem(ITEM_ID, modifyDto)
        }.apply {
            assertThat(message).isEqualTo("해당 상품을 찾을 수 없습니다.")
        }
    }

    @Test
    @DisplayName("상품 삭제 - 성공 케이스")
    fun deleteItem_success() {
        //given
        val item = Item("닭볶음탕", 18000L, ITEM_ID)
        Mockito.`when`(
            itemRepository.findNullableById(anyVararg())
        ).thenReturn(item)

        //when then
        itemPersistenceAdapter.deleteItem(anyVararg())

        //then
        Mockito.verify(itemRepository, Mockito.atLeastOnce())
            .delete(anyVararg())
    }

    @Test
    @DisplayName("상품 삭제 - 실패 케이스")
    fun deleteItem_fail() {
        //given
        Mockito.`when`(
            itemRepository.findNullableById(anyVararg())
        ).thenReturn(null)

        //when then
        assertThrows<IllegalArgumentException> {
            itemPersistenceAdapter.deleteItem(ITEM_ID)
        }.apply {
            assertThat(message).isEqualTo("존재하지 않는 상품입니다.")
        }
        Mockito.verify(itemRepository, Mockito.never())
            .delete(anyVararg())
    }

    companion object {
        const val ITEM_ID = 1L
    }

}