package me.hero.minicommerce.item.adapter.in.web.dto;

import lombok.Data;
import me.hero.minicommerce.item.application.port.in.dto.FindItemDto;

@Data
public class FindItemResponse {
  private final String name;
  private final Long price;

  public FindItemResponse(FindItemDto itemDto) {
    this.name = itemDto.getName();
    this.price = itemDto.getPrice();
  }
}
