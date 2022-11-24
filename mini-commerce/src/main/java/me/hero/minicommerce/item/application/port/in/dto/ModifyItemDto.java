package me.hero.minicommerce.item.application.port.in.dto;


import lombok.Value;
import me.hero.minicommerce.item.adapter.out.persistance.dto.ItemModifyParams;

@Value
public class ModifyItemDto {
  private final String name;
  private final Long price;

  public ModifyItemDto(String name, Long price) {
    this.name = name;
    this.price = price;
  }

  public ItemModifyParams toDto() {
    return ItemModifyParams.builder()
        .name(name)
        .price(price)
        .build();
  }
}
