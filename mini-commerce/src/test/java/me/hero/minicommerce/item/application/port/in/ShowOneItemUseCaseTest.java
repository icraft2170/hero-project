package me.hero.minicommerce.item.application.port.in;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import me.hero.minicommerce.item.application.port.in.dto.FindItemDto;
import me.hero.minicommerce.item.application.port.out.FindOneItemPort;
import me.hero.minicommerce.item.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class ShowOneItemUseCaseTest {

  public static final long ITEM_ID = 1L;
  @InjectMocks
  ShowOneItemUseCase showOneItemUseCase;

  @Mock
  FindOneItemPort findOneItemPort;


  @Test
  @DisplayName("상품 조회 - 성공 케이스")
  void getItemService_success() {
    //given
    Item item = new Item("닭볶음탕", 18000L);
    given(findOneItemPort.findOneItem(any())).willReturn(Optional.of(item));

    //when
    FindItemDto findItem = showOneItemUseCase.showOneItem(ITEM_ID);

    //then
    assertThat(item.getName()).isEqualTo(findItem.getName());
    assertThat(item.getPrice()).isEqualTo(findItem.getPrice());
  }

  @Test
  @DisplayName("상품 조회 - 해당 값이 없는 케이스")
  void getItemService_fail() {
    //given
    given(findOneItemPort.findOneItem(any())).willReturn(Optional.empty());

    //when then
    assertThatCode(() -> showOneItemUseCase.showOneItem(ITEM_ID))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("해당 상품을 찾을 수 없습니다.");
  }

}