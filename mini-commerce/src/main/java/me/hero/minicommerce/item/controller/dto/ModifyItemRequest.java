package me.hero.minicommerce.item.controller.dto;

import lombok.Data;

@Data
public class ModifyItemRequest {
  private String name;
  private Long price;
}
