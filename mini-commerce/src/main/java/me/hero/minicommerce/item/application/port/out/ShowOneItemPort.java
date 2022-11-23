package me.hero.minicommerce.item.application.port.out;

import java.util.Optional;
import me.hero.minicommerce.item.domain.Item;

public interface ShowOneItemPort {

  Optional<Item> findOneItem(long itemId);
}
