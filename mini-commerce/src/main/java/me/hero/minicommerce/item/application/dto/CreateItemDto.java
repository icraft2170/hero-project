package me.hero.minicommerce.item.application.dto;

import lombok.Builder;
import lombok.Value;
import me.hero.minicommerce.item.domain.Item;

@Value
public class CreateItemDto {
  private final Long id;
  private final String name;
  private final Long price;

  @Builder
  public CreateItemDto(Long id, String name, Long price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  /**
   * Mapper 를 따로 두지 않고. Item을 의존성으로 가지고 있는 방식
   */
  public CreateItemDto(Item item) {
    this.id = item.getId();
    this.name = item.getName();
    this.price = item.getPrice();
  }
  public Item toEntity() {
    return Item.builder()
        .name(name)
        .price(price)
        .build();
  }
}
