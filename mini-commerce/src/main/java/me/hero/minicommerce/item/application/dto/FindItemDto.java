package me.hero.minicommerce.item.application.dto;

import lombok.Getter;
import lombok.Value;
import me.hero.minicommerce.item.domain.Item;

@Getter
@Value
public class FindItemDto {
  private final String name;
  private final Long price;

  public FindItemDto(Item item) {
    this.name = item.getName();
    this.price = item.getPrice();
  }

}
