package me.hero.minicommerce.item.service.dto;


import lombok.Value;

@Value
public class ModifyItemDto {
  private final String name;
  private final Long price;

  public ModifyItemDto(String name, Long price) {
    this.name = name;
    this.price = price;
  }
}
