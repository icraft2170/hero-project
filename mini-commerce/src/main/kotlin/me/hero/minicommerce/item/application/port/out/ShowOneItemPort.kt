package me.hero.minicommerce.item.application.port.out

import me.hero.minicommerce.item.domain.Item

interface ShowOneItemPort {
    fun findOneItem(itemId: Long): Item
}