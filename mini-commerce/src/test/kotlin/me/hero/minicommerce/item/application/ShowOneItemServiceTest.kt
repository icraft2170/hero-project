package me.hero.minicommerce.item.application

import me.hero.minicommerce.item.application.port.`in`.dto.FindItemDto
import me.hero.minicommerce.item.application.port.out.ShowOneItemPort
import me.hero.minicommerce.item.domain.Item
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.anyVararg

@DisplayName("상품 조회 서비스 테스트")
@ExtendWith(MockitoExtension::class)
class ShowOneItemServiceTest{
    @InjectMocks
    lateinit var showOneItemUseCase: ShowOneItemService
    @Mock
    lateinit var showOneItemPort: ShowOneItemPort



    @DisplayName("상품 조회 - 성공 케이스")
    @Test
    fun itemService_success() {
        //given
        val item = Item("닭볶음탕", 18000L, ITEM_ID)
        Mockito.`when`(
            showOneItemPort.findOneItem(anyVararg())
        ).thenReturn(item)


        //when
        val findItem: FindItemDto = showOneItemUseCase.showOneItem(ITEM_ID)

        //then
        assertThat(item.name).isEqualTo(findItem.name)
        assertThat(item.price).isEqualTo(findItem.price)
    }


    companion object {
        const val ITEM_ID = 1L
    }
}