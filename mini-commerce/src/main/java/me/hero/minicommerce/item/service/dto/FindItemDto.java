package me.hero.minicommerce.item.service.dto;

import lombok.Getter;
import lombok.Value;
import me.hero.minicommerce.item.domain.Item;

@Getter
@Value
public class FindItemDto {
  private final String name;
  private final Long price;

  public FindItemDto(String name, Long price) {
    this.name = name;
    this.price = price;
  }

  public Item toEntity() {
    return new Item(name, price);
  }
}
