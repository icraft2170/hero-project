package me.hero.minicommerce.item.service.dto;

import lombok.Value;
import me.hero.minicommerce.item.domain.Item;

@Value
public class ModifiedItemDto {
  private final Long id;
  private final String name;
  private final Long price;

  public ModifiedItemDto(Item item) {
    this.id = item.getId();
    this.name = item.getName();
    this.price = item.getPrice();
  }
}
