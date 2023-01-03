package me.hero.minicommerce.item.application.port.`in`

import me.hero.minicommerce.item.application.port.`in`.dto.ModifiedItemDto
import me.hero.minicommerce.item.application.port.`in`.dto.ModifyItemDto


interface ModifyItemUseCase {
    fun modifyItem(itemId: Long, modifyItemDto: ModifyItemDto): ModifiedItemDto
}