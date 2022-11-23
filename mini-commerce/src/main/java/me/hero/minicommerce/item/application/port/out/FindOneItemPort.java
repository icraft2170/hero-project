package me.hero.minicommerce.item.application.port.out;

import java.util.Optional;
import me.hero.minicommerce.item.domain.Item;

public interface FindOneItemPort {

  Optional<Item> findOneItem(long itemId);
}
