package me.hero.minicommerce.item.application;

import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.domain.Item;
import me.hero.minicommerce.item.domain.dto.ItemModifyParams;
import me.hero.minicommerce.item.application.port.in.dto.CreateItemDto;
import me.hero.minicommerce.item.adapter.out.persistance.ItemRepository;
import me.hero.minicommerce.item.application.port.in.dto.FindItemDto;
import me.hero.minicommerce.item.application.port.in.dto.ModifiedItemDto;
import me.hero.minicommerce.item.application.port.in.dto.ModifyItemDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {
  private final ItemRepository itemRepository;

  @Transactional
  public ModifiedItemDto modifyItem(Long itemId, ModifyItemDto modifyDto) {
    Item findItem = itemRepository.findById(itemId).orElseThrow(RuntimeException::new);
    ItemModifyParams params = modifyDto.toDto();
    findItem.modify(params);
    return new ModifiedItemDto(findItem);
  }


  public void deleteItem(long itemId) {
    Item findItem = itemRepository.findById(itemId)
        .orElseThrow(() -> new IllegalArgumentException("이미 존재하지 않는 상품입니다."));
    itemRepository.delete(findItem);
  }
}
