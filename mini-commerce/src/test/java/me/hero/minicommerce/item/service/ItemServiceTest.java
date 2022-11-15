package me.hero.minicommerce.item.service;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import me.hero.minicommerce.item.domain.Item;
import me.hero.minicommerce.item.repository.ItemRepository;
import me.hero.minicommerce.item.service.dto.ModifiedItemDto;
import me.hero.minicommerce.item.service.dto.ModifyItemDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
class ItemServiceTest {

  @Autowired
  private ItemService itemService;

  @Mock
  private ItemRepository itemRepository;

  @Test
  @DisplayName("상품 수정 - 성공 케이스")
  void modifyItem() {
    //given
    Item item = new Item("닭볶음탕", 18000L);
    ModifyItemDto modifyDto = new ModifyItemDto("닭볶음탕", 20000L);
    given(itemRepository.findById(any())).willReturn(Optional.of(item));
    //when
    ModifiedItemDto dto = itemService.modifyItem(modifyDto);

    //then
    assertThat(dto.getName()).isEqualTo(modifyDto.getName());
    assertThat(dto.getPrice()).isEqualTo(modifyDto.getPrice());
  }
}