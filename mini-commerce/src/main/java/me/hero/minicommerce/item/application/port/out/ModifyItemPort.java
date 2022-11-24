package me.hero.minicommerce.item.application.port.out;

import me.hero.minicommerce.item.application.port.in.dto.ModifiedItemDto;
import me.hero.minicommerce.item.application.port.in.dto.ModifyItemDto;
import me.hero.minicommerce.item.domain.Item;

public interface ModifyItemPort {
  Item modifyItem(Long itemId, ModifyItemDto modifyItemDto);
}
