package me.hero.minicommerce.item.application

import me.hero.minicommerce.item.application.port.`in`.dto.ModifiedItemDto
import me.hero.minicommerce.item.application.port.`in`.dto.ModifyItemDto
import me.hero.minicommerce.item.application.port.out.ModifyItemPort
import me.hero.minicommerce.item.domain.Item
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@DisplayName("상품 수정 서비스 테스트")
@ExtendWith(MockitoExtension::class)
internal class ModifyItemServiceTest{
    @InjectMocks lateinit var modifyItemService: ModifyItemService
    @Mock lateinit var modifyItemPort: ModifyItemPort

    @Test
    @DisplayName("상품 수정 - 성공 케이스")
    fun modifyItem() {
        //given
        val modifyDto = ModifyItemDto("닭볶음탕", 20000L)
        val modifiedItem = Item("닭볶음탕", 20000L, ITEM_ID)
        BDDMockito.given(
            modifyItemPort.modifyItem(
                ArgumentMatchers.any(),
                ArgumentMatchers.any()
            )
        ).willReturn(modifiedItem)

        //when
        val dto: ModifiedItemDto = modifyItemService.modifyItem(ITEM_ID, modifyDto)

        //then
        Assertions.assertThat(dto.name).isEqualTo(modifiedItem.name)
        Assertions.assertThat(dto.price).isEqualTo(modifiedItem.price)
    }

    companion object {
        const val ITEM_ID = 1L
    }
}