package me.hero.minicommerce.item.application.port.in;

import me.hero.minicommerce.item.application.port.in.dto.FindItemDto;

public interface ShowOneItemUseCase {
  FindItemDto showOneItem(long itemId);
}
