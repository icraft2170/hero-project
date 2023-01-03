package me.hero.minicommerce.item.application

import me.hero.minicommerce.item.application.port.`in`.ShowOneItemUseCase
import me.hero.minicommerce.item.application.port.`in`.dto.FindItemDto
import me.hero.minicommerce.item.application.port.out.ShowOneItemPort
import org.springframework.stereotype.Service

@Service
class ShowOneItemService (
    private val showOneItemPort: ShowOneItemPort,
) : ShowOneItemUseCase{

    override fun showOneItem(itemId: Long): FindItemDto {
        val findItem = showOneItemPort.findOneItem(itemId)
        return FindItemDto(findItem)
    }
}