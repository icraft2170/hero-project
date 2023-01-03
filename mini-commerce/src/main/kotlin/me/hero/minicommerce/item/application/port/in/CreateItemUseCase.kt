package me.hero.minicommerce.item.application.port.`in`

import me.hero.minicommerce.item.application.port.`in`.dto.CreateItemDto

interface CreateItemUseCase {
    fun createItem(itemDto: CreateItemDto): CreateItemDto
}