package me.hero.minicommerce.item.application.port.`in`.dto

import me.hero.minicommerce.item.domain.Item


data class CreateItemDto(
    val id: Long? = null,
    val name: String,
    val price: Long,
) {
    constructor(item: Item) : this(
        id = item.id,
        name = item.name,
        price = item.price,
    )

    fun toEntity(): Item {
        return Item(
            name = name,
            price = price,
        )
    }
}
