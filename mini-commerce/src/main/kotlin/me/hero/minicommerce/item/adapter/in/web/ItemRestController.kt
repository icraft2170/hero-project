package me.hero.minicommerce.item.adapter.`in`.web

import me.hero.minicommerce.item.adapter.`in`.web.dto.*
import me.hero.minicommerce.item.application.port.`in`.CreateItemUseCase
import me.hero.minicommerce.item.application.port.`in`.DeleteItemUseCase
import me.hero.minicommerce.item.application.port.`in`.ModifyItemUseCase
import me.hero.minicommerce.item.application.port.`in`.ShowOneItemUseCase
import me.hero.minicommerce.item.application.port.`in`.dto.ModifyItemDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/items")
class ItemRestController (
    private val deleteItemUseCase: DeleteItemUseCase,
    private val createItemUseCase: CreateItemUseCase,
    private val showOneItemUseCase: ShowOneItemUseCase,
    private val modifyItemUseCase: ModifyItemUseCase,
) {

    @PostMapping
    fun createItem(
        @RequestBody request: CreateItemRequest,
    ): ResponseEntity<CreateItemResponse> {
        val itemDto = createItemUseCase.createItem(request.toDto())
        val createItemResponse = CreateItemResponse(itemDto)

        return ResponseEntity
            .created(URI.create("/items/" + createItemResponse.id))
            .body(createItemResponse)
    }

    @PutMapping("/{itemId}")
    fun modifyItem(
        @RequestBody request: ModifyItemRequest,
        @PathVariable itemId: Long,
    ): ResponseEntity<ModifyItemResponse> {
        val modifyItemDto = ModifyItemDto(request.name, request.price)
        val modifiedItemDto = modifyItemUseCase.modifyItem(itemId, modifyItemDto)

        return ResponseEntity
            .ok(ModifyItemResponse(modifiedItemDto))
    }

    @GetMapping("/{itemId}")
    fun findItem(
        @PathVariable itemId: Long,
    ): ResponseEntity<FindItemResponse>? {
        val findItem = showOneItemUseCase.showOneItem(itemId)

        return ResponseEntity
            .ok(FindItemResponse(findItem))
    }

    @DeleteMapping("/{itemId}")
    fun deleteItem(
        @PathVariable itemId: Long,
    ): ResponseEntity<Unit> {
        deleteItemUseCase.deleteItem(itemId)

        return ResponseEntity.ok(Unit)
    }

}