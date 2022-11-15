package me.hero.minicommerce.item.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.hero.minicommerce.item.service.dto.ModifiedItemDto;

@Data @NoArgsConstructor
public class ModifyItemResponse {
  private Long id;
  private String name;
  private Long price;
  public ModifyItemResponse(ModifiedItemDto dto) {
    this.id = dto.getId();
    this.name = dto.getName();
    this.price = dto.getPrice();
  }
}
