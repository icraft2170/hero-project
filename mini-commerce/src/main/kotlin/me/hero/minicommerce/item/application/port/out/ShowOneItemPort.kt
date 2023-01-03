package me.hero.minicommerce.item.application.port.out

import me.hero.minicommerce.item.domain.Item
import java.util.Optional

interface ShowOneItemPort {
    fun findOneItem(itemId: Long): Optional<Item>
}