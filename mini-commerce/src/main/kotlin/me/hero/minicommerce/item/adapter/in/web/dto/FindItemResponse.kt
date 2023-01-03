package me.hero.minicommerce.item.adapter.`in`.web.dto

import me.hero.minicommerce.item.application.port.`in`.dto.FindItemDto

data class FindItemResponse(
    val name: String,
    val price: Long,
) {
    constructor(itemDto: FindItemDto) : this(
        name = itemDto.name,
        price = itemDto.price,
    )
}