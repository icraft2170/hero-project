package me.hero.minicommerce.item.adapter.in.web.dto;

import lombok.Data;

@Data
public class ModifyItemRequest {
  private String name;
  private Long price;
}
