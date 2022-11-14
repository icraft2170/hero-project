package me.hero.minicommerce.item.service;

import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.domain.Item;
import me.hero.minicommerce.item.domain.dto.CreateItemResponse;
import me.hero.minicommerce.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
  private final ItemRepository itemRepository;

  public CreateItemResponse createItem(Item item) {
    Item savedItem = itemRepository.save(item);
    return new CreateItemResponse(savedItem);
  }

}
