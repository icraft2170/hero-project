package me.hero.minicommerce.item.domain.dto;

import lombok.Value;
import me.hero.minicommerce.item.domain.Item;

@Value
public class CreateItemResponse {
  private final Long id;
  private final String name;
  private final Long price;

  /**
   * Mapper 를 따로 두지 않고. Item을 의존성으로 가지고 있는 방식
   */
  public CreateItemResponse(Item item) {
    this.id = item.getId();
    this.name = item.getName();
    this.price = item.getPrice();
  }
}
