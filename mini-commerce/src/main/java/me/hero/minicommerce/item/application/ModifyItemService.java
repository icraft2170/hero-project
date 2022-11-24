package me.hero.minicommerce.item.application;

import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.application.port.in.ModifyItemUseCase;
import me.hero.minicommerce.item.application.port.in.dto.ModifiedItemDto;
import me.hero.minicommerce.item.application.port.in.dto.ModifyItemDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyItemService implements ModifyItemUseCase {

  @Override
  public ModifiedItemDto modifyItem(Long itemId, ModifyItemDto modifyItemDto) {
    return null;
  }
}
