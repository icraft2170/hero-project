package me.hero.minicommerce.item.application;

import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.application.port.in.CreateItemUseCase;
import me.hero.minicommerce.item.application.port.in.dto.CreateItemDto;
import me.hero.minicommerce.item.application.port.out.CreateItemPort;
import me.hero.minicommerce.item.domain.Item;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateItemService implements CreateItemUseCase {
  private final CreateItemPort createItemPort;

  @Override
  public CreateItemDto createItem(CreateItemDto itemDto) {
    Item savedItem = createItemPort.saveItem(itemDto.toEntity());
    return new CreateItemDto(savedItem);
  }
}
