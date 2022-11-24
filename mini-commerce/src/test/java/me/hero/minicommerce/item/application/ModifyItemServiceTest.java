package me.hero.minicommerce.item.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import me.hero.minicommerce.item.application.port.in.dto.ModifiedItemDto;
import me.hero.minicommerce.item.application.port.in.dto.ModifyItemDto;
import me.hero.minicommerce.item.application.port.out.ModifyItemPort;
import me.hero.minicommerce.item.application.port.out.ShowOneItemPort;
import me.hero.minicommerce.item.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class ModifyItemServiceTest {

  public static final long ITEM_ID = 1L;

  @InjectMocks
  ModifyItemService modifyItemService;
  @Mock
  ModifyItemPort modifyItemPort;

  @Test
  @DisplayName("상품 수정 - 성공 케이스")
  void modifyItem() {
    //given
    ModifyItemDto modifyDto = new ModifyItemDto("닭볶음탕", 20000L);
    Item modifiedItem = new Item("닭볶음탕", 20000L);
    given(modifyItemPort.modifyItem(any(), any())).willReturn(modifiedItem);

    //when
    ModifiedItemDto dto = modifyItemService.modifyItem(ITEM_ID, modifyDto);

    //then
    assertThat(dto.getName()).isEqualTo(modifiedItem.getName());
    assertThat(dto.getPrice()).isEqualTo(modifiedItem.getPrice());
  }


}