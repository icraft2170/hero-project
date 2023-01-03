package me.hero.minicommerce.item.adapter.`in`.web.dto

import me.hero.minicommerce.item.application.port.`in`.dto.CreateItemDto

data class CreateItemResponse(
    val id: Long,
    val name: String,
    val price: Long,
) {
    constructor(dto: CreateItemDto) : this(
        id = dto.id!!,
        name = dto.name,
        price = dto.price,
    )
}