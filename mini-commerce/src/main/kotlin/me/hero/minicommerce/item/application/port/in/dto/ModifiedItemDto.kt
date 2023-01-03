package me.hero.minicommerce.item.application.port.`in`.dto

import me.hero.minicommerce.item.domain.Item

data class ModifiedItemDto(
    val id: Long,
    val name: String,
    val price: Long,
) {
    constructor(item: Item) : this(
        id = item.id!!,
        name = item.name,
        price = item.price,
    )
}
