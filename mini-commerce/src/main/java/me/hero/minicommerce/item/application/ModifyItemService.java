package me.hero.minicommerce.item.application;

import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.application.port.in.ModifyItemUseCase;
import me.hero.minicommerce.item.application.port.in.dto.ModifiedItemDto;
import me.hero.minicommerce.item.application.port.in.dto.ModifyItemDto;
import me.hero.minicommerce.item.application.port.out.ModifyItemPort;
import me.hero.minicommerce.item.domain.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class ModifyItemService implements ModifyItemUseCase {
  private final ModifyItemPort modifyItemPort;

  @Override
  public ModifiedItemDto modifyItem(Long itemId, ModifyItemDto modifyItemDto) {
    Item modifiedItem = modifyItemPort.modifyItem(itemId, modifyItemDto);
    return new ModifiedItemDto(modifiedItem);
  }
}