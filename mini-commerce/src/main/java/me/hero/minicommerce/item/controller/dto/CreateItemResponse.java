package me.hero.minicommerce.item.controller.dto;

import lombok.Value;
import me.hero.minicommerce.item.service.dto.CreateItemDto;

@Value
public class CreateItemResponse {
  private final Long id;
  private final String name;
  private final Long price;

  public CreateItemResponse(CreateItemDto dto) {
    this.id = dto.getId();
    this.name = dto.getName();
    this.price = dto.getPrice();
  }
}
