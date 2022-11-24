package me.hero.minicommerce.item.application.port.in;

import me.hero.minicommerce.item.application.port.in.dto.ModifiedItemDto;
import me.hero.minicommerce.item.application.port.in.dto.ModifyItemDto;

public interface ModifyItemUseCase {
  ModifiedItemDto modifyItem(Long itemId, ModifyItemDto modifyItemDto);
}
