package me.hero.minicommerce.item.adapter.out.persistance

import me.hero.minicommerce.item.application.port.`in`.dto.ModifyItemDto
import me.hero.minicommerce.item.application.port.out.CreateItemPort
import me.hero.minicommerce.item.application.port.out.DeleteItemPort
import me.hero.minicommerce.item.application.port.out.ModifyItemPort
import me.hero.minicommerce.item.application.port.out.ShowOneItemPort
import me.hero.minicommerce.item.domain.Item
import org.springframework.stereotype.Component

@Component
class ItemPersistenceAdapter (
    private val itemRepository: ItemRepository,
 ) : CreateItemPort, ShowOneItemPort, ModifyItemPort, DeleteItemPort{

    override fun saveItem(item: Item): Item {
        return itemRepository.save(item)
    }

    override fun findOneItem(itemId: Long): Item {
        return itemRepository.findNullableById(itemId) ?:
            throw IllegalArgumentException("해당 상품을 찾을 수 없습니다.")
    }


    override fun deleteItem(itemId: Long) {
        val findItem = itemRepository.findNullableById(itemId)
            ?: throw IllegalArgumentException("존재하지 않는 상품입니다.")
        itemRepository.delete(findItem)
    }

    override fun modifyItem(itemId: Long, modifyItemDto: ModifyItemDto): Item {
        val modifiedItem = itemRepository.findNullableById(itemId)
            ?: throw IllegalArgumentException("해당 상품을 찾을 수 없습니다.")

        val params = modifyItemDto.toParams()
        modifiedItem.modify(params)
        return modifiedItem
    }

}