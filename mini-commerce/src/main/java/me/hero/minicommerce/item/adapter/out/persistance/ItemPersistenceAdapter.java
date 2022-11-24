package me.hero.minicommerce.item.adapter.out.persistance;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.adapter.out.persistance.dto.ItemModifyParams;
import me.hero.minicommerce.item.application.port.in.dto.ModifyItemDto;
import me.hero.minicommerce.item.application.port.out.CreateItemPort;
import me.hero.minicommerce.item.application.port.out.DeleteItemPort;
import me.hero.minicommerce.item.application.port.out.ModifyItemPort;
import me.hero.minicommerce.item.application.port.out.ShowOneItemPort;
import me.hero.minicommerce.item.domain.Item;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ItemPersistenceAdapter implements CreateItemPort, ShowOneItemPort, ModifyItemPort,
    DeleteItemPort {

  private final ItemRepository itemRepository;

  @Override
  public Item saveItem(Item item) {
    return itemRepository.save(item);
  }

  @Override
  public Optional<Item> findOneItem(long itemId) {
    return itemRepository.findById(itemId);
  }

  @Override
  public Item modifyItem(Long itemId, ModifyItemDto modifyItemDto) {
    Item modifiedItem = itemRepository.findById(itemId).orElseThrow(RuntimeException::new);
    ItemModifyParams params = modifyItemDto.toDto();
    modifiedItem.modify(params);
    return modifiedItem;
  }

  @Override
  public void deleteItem(long itemId) {
    Item findItem = itemRepository.findById(itemId)
        .orElseThrow(() -> new IllegalArgumentException("이미 존재하지 않는 상품입니다."));
    itemRepository.delete(findItem);
  }
}
