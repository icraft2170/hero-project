package me.hero.minicommerce.item.application

import me.hero.minicommerce.item.application.port.`in`.DeleteItemUseCase
import me.hero.minicommerce.item.application.port.out.DeleteItemPort
import org.springframework.stereotype.Service

@Service
class DeleteItemService (
    private val deleteItemPort: DeleteItemPort,
) : DeleteItemUseCase{

    override fun deleteItem(itemId: Long) {
        deleteItemPort.deleteItem(itemId)
    }
}