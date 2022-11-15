package me.hero.minicommerce.item.service;

import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.domain.Item;
import me.hero.minicommerce.item.domain.dto.ItemModifyParams;
import me.hero.minicommerce.item.service.dto.CreateItemDto;
import me.hero.minicommerce.item.repository.ItemRepository;
import me.hero.minicommerce.item.service.dto.ModifiedItemDto;
import me.hero.minicommerce.item.service.dto.ModifyItemDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {
  private final ItemRepository itemRepository;

  public CreateItemDto createItem(CreateItemDto itemDto) {
    Item savedItem = itemRepository.save(itemDto.toEntity());
    return new CreateItemDto(savedItem);
  }

  @Transactional
  public ModifiedItemDto modifyItem(Long itemId, ModifyItemDto modifyDto) {
    Item findItem = itemRepository.findById(itemId).orElseThrow(RuntimeException::new);
    ItemModifyParams params = modifyDto.toDto();
    findItem.modify(params);
    return new ModifiedItemDto(findItem);
  }
}
