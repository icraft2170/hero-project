package me.hero.minicommerce.item.domain;

import static org.assertj.core.api.Assertions.*;

import me.hero.minicommerce.item.domain.dto.ItemModifyParams;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemTest {


  @Test
  @DisplayName("상품 수정 modify() 메소드 단위테스트")
  void modifyItem() {
    //given
    Item newItem = new Item("닭도리탕", 18000L);
    ItemModifyParams params = new ItemModifyParams("닭도리탕", 20000L);

    //when
    newItem.modify(params);

    //then
    assertThat(newItem.getName()).isEqualTo(params.getName());
    assertThat(newItem.getPrice()).isEqualTo(params.getPrice());
  }
}