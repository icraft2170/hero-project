package me.hero.minicommerce.item.adapter.`in`.web.dto

import me.hero.minicommerce.item.application.port.`in`.dto.CreateItemDto


data class CreateItemRequest (
    val name: String,
    val price: Long,

) {
    fun toDto(): CreateItemDto {
        return CreateItemDto(
            name = name,
            price = price,
        )
    }
}