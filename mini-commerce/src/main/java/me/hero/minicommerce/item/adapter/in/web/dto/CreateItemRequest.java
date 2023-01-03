package me.hero.minicommerce.item.adapter.in.web.dto;

import lombok.Data;
import me.hero.minicommerce.item.application.port.in.dto.CreateItemDto;

@Data
public class CreateItemRequest {
  private String name;
  private Long price;

  public CreateItemRequest() {}

  public CreateItemDto toDto() {
    return new CreateItemDto(null, name, price);
  }

}
