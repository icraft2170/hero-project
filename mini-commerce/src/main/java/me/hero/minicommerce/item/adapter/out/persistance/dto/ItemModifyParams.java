package me.hero.minicommerce.item.adapter.out.persistance.dto;

import java.util.Objects;
import lombok.Builder;
import lombok.Value;

@Value
public class ItemModifyParams {
  private final String name;
  private final Long price;

  public String getName() {
    return name;
  }

  public Long getPrice() {
    return price;
  }

  @Builder
  public ItemModifyParams(String name, Long price) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(price);
    this.name = name;
    this.price = price;
  }
}
