package me.hero.minicommerce.item.application

import me.hero.minicommerce.item.application.port.`in`.ModifyItemUseCase
import me.hero.minicommerce.item.application.port.`in`.dto.ModifiedItemDto
import me.hero.minicommerce.item.application.port.`in`.dto.ModifyItemDto
import me.hero.minicommerce.item.application.port.out.ModifyItemPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ModifyItemService (
    private val modifyItemPort: ModifyItemPort,
) : ModifyItemUseCase{

    override fun modifyItem(itemId: Long, modifyItemDto: ModifyItemDto): ModifiedItemDto {
        val modifiedItem = modifyItemPort.modifyItem(itemId, modifyItemDto)
        return ModifiedItemDto(modifiedItem)
    }
}