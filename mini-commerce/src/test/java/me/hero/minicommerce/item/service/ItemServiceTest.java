package me.hero.minicommerce.item.service;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import me.hero.minicommerce.item.domain.Item;
import me.hero.minicommerce.item.repository.ItemRepository;
import me.hero.minicommerce.item.service.dto.FindItemDto;
import me.hero.minicommerce.item.service.dto.ModifiedItemDto;
import me.hero.minicommerce.item.service.dto.ModifyItemDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class ItemServiceTest {
  @InjectMocks
  ItemService itemService;

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
    ModifiedItemDto dto = itemService.modifyItem(1L, modifyDto);

    //then
    assertThat(dto.getName()).isEqualTo(modifyDto.getName());
    assertThat(dto.getPrice()).isEqualTo(modifyDto.getPrice());
  }

  @Test
  @DisplayName("상품 수정 - 실패 케이스 ( Repository.find()가 null 일 때 )")
  void failModifyItem() {
    //given
    Item item = new Item("닭볶음탕", 18000L);
    ModifyItemDto modifyDto = new ModifyItemDto("닭볶음탕", 20000L);
    given(itemRepository.findById(any())).willReturn(Optional.empty());
    //when then
    assertThatCode(() -> itemService.modifyItem(1L, modifyDto))
        .isInstanceOf(RuntimeException.class);
  }

  @Test
  @DisplayName("상품 조회 - 성공 케이스")
  void getItemService_success() {
    //given
    Item item = new Item("닭볶음탕", 18000L);
    given(itemRepository.findById(any())).willReturn(Optional.of(item));

    //when
    FindItemDto findItem = itemService.getItem(1L);

    //then
    assertThat(item.getName()).isEqualTo(findItem.getName());
    assertThat(item.getPrice()).isEqualTo(findItem.getPrice());
  }

  @Test
  @DisplayName("상품 조회 - 해당 값이 없는 케이스")
  void getItemService_fail() {
    //given
    given(itemRepository.findById(any())).willReturn(Optional.empty());

    //when then
    assertThatCode(() -> itemService.getItem(1L))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("해당 상품을 찾을 수 없습니다.");
  }
}