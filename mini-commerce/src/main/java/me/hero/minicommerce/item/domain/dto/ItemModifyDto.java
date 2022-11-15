package me.hero.minicommerce.item.domain.dto;

import java.util.Objects;
import lombok.Builder;
import lombok.Value;

@Value
public class ItemModifyDto {
  private final String name;
  private final Long price;
  public ItemModifyDto(String name, Long price) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(price);
    this.name = name;
    this.price = price;
  }
}
