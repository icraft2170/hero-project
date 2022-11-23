package me.hero.minicommerce.item.adapter.out.persistance;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.application.port.out.CreateItemPort;
import me.hero.minicommerce.item.application.port.out.ShowOneItemPort;
import me.hero.minicommerce.item.domain.Item;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ItemPersistenceAdapter implements CreateItemPort, ShowOneItemPort {

  private final ItemRepository itemRepository;

  @Override
  public Item saveItem(Item item) {
    return itemRepository.save(item);
  }

  @Override
  public Optional<Item> findOneItem(long itemId) {
    return itemRepository.findById(itemId);
  }
}
