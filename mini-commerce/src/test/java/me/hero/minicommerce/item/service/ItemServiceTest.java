package me.hero.minicommerce.item.service;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import me.hero.minicommerce.item.application.ItemService;
import me.hero.minicommerce.item.domain.Item;
import me.hero.minicommerce.item.adapter.out.persistance.ItemRepository;
import me.hero.minicommerce.item.application.port.in.dto.FindItemDto;
import me.hero.minicommerce.item.application.port.in.dto.ModifiedItemDto;
import me.hero.minicommerce.item.application.port.in.dto.ModifyItemDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class ItemServiceTest {

  public static final long ITEM_ID = 1L;
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
    ModifiedItemDto dto = itemService.modifyItem(ITEM_ID, modifyDto);

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
    assertThatCode(() -> itemService.modifyItem(ITEM_ID, modifyDto))
        .isInstanceOf(RuntimeException.class);
  }


  @Test
  @DisplayName("상품 삭제 - 성공 케이스")
  void deleteItem_success() {
    //given
    Item item = new Item("닭볶음탕", 18000L);
    given(itemRepository.findById(any())).willReturn(Optional.of(item));
    //when then
    itemService.deleteItem(ITEM_ID);
    //then
    verify(itemRepository, atLeastOnce()).delete(any());
  }

  @Test
  @DisplayName("상품 삭제 - 실패 케이스")
  void deleteItem_fail() {
    //given
    given(itemRepository.findById(any())).willReturn(Optional.empty());
    //when then
    assertThatCode(() -> itemService.deleteItem(ITEM_ID))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("이미 존재하지 않는 상품입니다.");
    verify(itemRepository, never()).delete(any());
  }
}