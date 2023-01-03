package me.hero.minicommerce.item.application.port.out

interface DeleteItemPort {
    fun deleteItem(itemId: Long)
}