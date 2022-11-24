package me.hero.minicommerce.item.application.port.out;


import me.hero.minicommerce.item.domain.Item;

public interface CreateItemPort {

  Item saveItem(Item item);
}
