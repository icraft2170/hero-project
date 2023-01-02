package me.hero.minicommerce.item.adapter.out.persistance

import me.hero.minicommerce.item.application.port.`in`.dto.ModifyItemDto
import me.hero.minicommerce.item.domain.Item
import org.assertj.core.api.Assertions
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@DisplayName("상품 영속성 어댑터 테스트")
@ExtendWith(MockitoExtension::class)
class ItemPersistenceAdapterTest {
    @InjectMocks lateinit var itemPersistenceAdapter: ItemPersistenceAdapter
    @Mock lateinit var itemRepository: ItemRepository

    @Test
    @DisplayName("상품 수정 Adapter - 실패 케이스 ( Repository.find()가 null 일 때 )")
    fun failModifyItem() {
        //given
        val modifyDto = ModifyItemDto("닭볶음탕", 20000L)
        BDDMockito.given<Optional<Item>>(itemRepository.findById(ArgumentMatchers.any()))
            .willReturn(
                Optional.empty<Item>()
            )

        //when then
        AssertionsForClassTypes.assertThatCode {
            itemPersistenceAdapter.modifyItem(1L, modifyDto)
        }
            .isInstanceOf(RuntimeException::class.java)
    }

    @Test
    @DisplayName("상품 삭제 - 성공 케이스")
    fun deleteItem_success() {
        //given
        val item = Item("닭볶음탕", 18000L)
        BDDMockito.given<Optional<Item>>(itemRepository.findById(ArgumentMatchers.any()))
            .willReturn(
                Optional.of(item)
            )
        //when then
        itemPersistenceAdapter.deleteItem(1L)
        //then
        Mockito.verify(itemRepository, Mockito.atLeastOnce())
            .delete(ArgumentMatchers.any())
    }

    @Test
    @DisplayName("상품 삭제 - 실패 케이스")
    fun deleteItem_fail() {
        //given
        BDDMockito.given<Optional<Item>>(itemRepository.findById(ArgumentMatchers.any()))
            .willReturn(
                Optional.empty<Item>()
            )
        //when then
        Assertions.assertThatCode {
            itemPersistenceAdapter.deleteItem(
                1L
            )
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("이미 존재하지 않는 상품입니다.")
        Mockito.verify(itemRepository, Mockito.never())
            .delete(ArgumentMatchers.any())
    }
}