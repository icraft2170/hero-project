package me.hero.minicommerce.item.application

import me.hero.minicommerce.item.application.port.`in`.CreateItemUseCase
import me.hero.minicommerce.item.application.port.`in`.dto.CreateItemDto
import me.hero.minicommerce.item.application.port.out.CreateItemPort
import org.springframework.stereotype.Service

@Service
class CreateItemService(
    private val createItemPort: CreateItemPort
) : CreateItemUseCase {

    override fun createItem(itemDto: CreateItemDto): CreateItemDto {
        val savedItem = createItemPort.saveItem(itemDto.toEntity())
        return CreateItemDto(savedItem)
    }
}