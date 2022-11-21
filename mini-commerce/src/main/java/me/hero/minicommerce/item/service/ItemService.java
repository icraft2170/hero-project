package me.hero.minicommerce.item.service;

import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.domain.Item;
import me.hero.minicommerce.item.domain.dto.ItemModifyParams;
import me.hero.minicommerce.item.service.dto.CreateItemDto;
import me.hero.minicommerce.item.repository.ItemRepository;
import me.hero.minicommerce.item.service.dto.FindItemDto;
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

  public FindItemDto getItem(long itemId) {
    Item findItem = itemRepository.findById(itemId)
        .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다."));
    return new FindItemDto(findItem);
  }

  public void deleteItem(long itemId) {
    Item findItem = itemRepository.findById(itemId)
        .orElseThrow(() -> new IllegalArgumentException("이미 존재하지 않는 상품입니다."));
    itemRepository.delete(findItem);
  }
}
