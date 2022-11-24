package me.hero.minicommerce.item.application.port.in;

import me.hero.minicommerce.item.application.port.in.dto.CreateItemDto;

public interface CreateItemUseCase {
  CreateItemDto createItem(CreateItemDto itemDto);
}
