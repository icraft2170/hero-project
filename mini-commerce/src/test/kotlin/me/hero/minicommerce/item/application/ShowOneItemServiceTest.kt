package me.hero.minicommerce.item.application

import me.hero.minicommerce.item.application.port.`in`.dto.FindItemDto
import me.hero.minicommerce.item.application.port.out.ShowOneItemPort
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
import java.util.*

@DisplayName("상품 조회 서비스 테스트")
@ExtendWith(MockitoExtension::class)
class ShowOneItemServiceTest{
    @InjectMocks lateinit var showOneItemUseCase: ShowOneItemService
    @Mock lateinit var showOneItemPort: ShowOneItemPort



    @DisplayName("상품 조회 - 성공 케이스")
    @Test
    fun itemService_success() {
        //given
        val item = Item("닭볶음탕", 18000L, ITEM_ID)
        BDDMockito.given<Optional<Item>>(showOneItemPort.findOneItem(ArgumentMatchers.anyLong()))
            .willReturn(Optional.of(item))

        //when
        val findItem: FindItemDto = showOneItemUseCase.showOneItem(ITEM_ID)

        //then
        Assertions.assertThat(item.name).isEqualTo(findItem.name)
        Assertions.assertThat(item.price).isEqualTo(findItem.price)
    }

    @DisplayName("상품 조회 - 해당 값이 없는 케이스")
    @Test
    fun itemService_fail() {
        //given
        BDDMockito.given<Optional<Item>>(showOneItemPort.findOneItem(ArgumentMatchers.anyLong()))
            .willReturn(
                Optional.empty<Item>()
            )

        //when then
        Assertions.assertThatCode {
            showOneItemUseCase.showOneItem(ITEM_ID)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("해당 상품을 찾을 수 없습니다.")
    }


    companion object {
        const val ITEM_ID = 1L
    }
}