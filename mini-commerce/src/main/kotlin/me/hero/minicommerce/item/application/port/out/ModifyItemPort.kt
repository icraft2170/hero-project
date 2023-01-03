package me.hero.minicommerce.item.application.port.out

import me.hero.minicommerce.item.application.port.`in`.dto.ModifyItemDto
import me.hero.minicommerce.item.domain.Item

interface ModifyItemPort {
    fun modifyItem(itemId: Long, modifyItemDto: ModifyItemDto): Item
}