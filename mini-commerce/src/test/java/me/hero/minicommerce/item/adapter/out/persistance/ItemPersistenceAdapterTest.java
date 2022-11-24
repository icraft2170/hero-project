package me.hero.minicommerce.item.adapter.out.persistance;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import me.hero.minicommerce.item.application.ItemService;
import me.hero.minicommerce.item.application.port.in.dto.ModifyItemDto;
import me.hero.minicommerce.item.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class ItemPersistenceAdapterTest {
  @InjectMocks
  ItemPersistenceAdapter itemPersistenceAdapter;
  @Mock
  ItemRepository itemRepository;


  @Test
  @DisplayName("상품 수정 Adapter - 실패 케이스 ( Repository.find()가 null 일 때 )")
  void failModifyItem() {
    //given
    Item item = new Item("닭볶음탕", 18000L);
    ModifyItemDto modifyDto = new ModifyItemDto("닭볶음탕", 20000L);
    given(itemRepository.findById(any())).willReturn(Optional.empty());
    //when then
    assertThatCode(() -> itemPersistenceAdapter.modifyItem(1L, modifyDto))
        .isInstanceOf(RuntimeException.class);
  }


    @Test
    @DisplayName("상품 삭제 - 성공 케이스")
    void deleteItem_success() {
      //given
      Item item = new Item("닭볶음탕", 18000L);
      given(itemRepository.findById(any())).willReturn(Optional.of(item));
      //when then
      itemPersistenceAdapter.deleteItem(1L);
      //then
      verify(itemRepository, atLeastOnce()).delete(any());
    }

    @Test
    @DisplayName("상품 삭제 - 실패 케이스")
    void deleteItem_fail() {
      //given
      given(itemRepository.findById(any())).willReturn(Optional.empty());
      //when then
      Assertions.assertThatCode(() -> itemPersistenceAdapter.deleteItem(1L))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("이미 존재하지 않는 상품입니다.");
      verify(itemRepository, never()).delete(any());
    }


}