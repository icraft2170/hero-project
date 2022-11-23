package me.hero.minicommerce.item.adapter.out.persistance;

import me.hero.minicommerce.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
