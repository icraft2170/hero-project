package me.hero.minicommerce.item.application.port.out

import me.hero.minicommerce.item.domain.Item

interface CreateItemPort {
    fun saveItem(item: Item): Item
}