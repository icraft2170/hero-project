package me.hero.minicommerce.item.application.port.`in`.dto

import me.hero.minicommerce.item.adapter.out.persistance.dto.ItemModifyParams

data class ModifyItemDto(
    val name: String,
    val price: Long,
) {
    fun toParams(): ItemModifyParams {
        return ItemModifyParams(name, price)
    }
}
