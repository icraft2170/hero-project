package me.hero.minicommerce.item.service;

import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.domain.Item;
import me.hero.minicommerce.item.service.dto.CreateItemDto;
import me.hero.minicommerce.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
  private final ItemRepository itemRepository;

  public CreateItemDto createItem(CreateItemDto itemDto) {
    Item savedItem = itemRepository.save(itemDto.toEntity());
    return new CreateItemDto(savedItem);
  }

}
