package me.hero.minicommerce.item.application.port.`in`

import me.hero.minicommerce.item.application.port.`in`.dto.FindItemDto

interface ShowOneItemUseCase {
    fun showOneItem(itemId: Long): FindItemDto
}