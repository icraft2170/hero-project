package me.hero.minicommerce.item.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.controller.dto.CreateItemRequest;
import me.hero.minicommerce.item.controller.dto.CreateItemResponse;
import me.hero.minicommerce.item.controller.dto.ModifyItemRequest;
import me.hero.minicommerce.item.controller.dto.ModifyItemResponse;
import me.hero.minicommerce.item.service.ItemService;
import me.hero.minicommerce.item.service.dto.CreateItemDto;
import me.hero.minicommerce.item.service.dto.ModifiedItemDto;
import me.hero.minicommerce.item.service.dto.ModifyItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemRestController {

  private final ItemService itemService;

  @PostMapping("")
  public ResponseEntity<CreateItemResponse> createItem(@RequestBody CreateItemRequest request) {
    CreateItemDto itemDto = itemService.createItem(request.toDto());
    CreateItemResponse createItemResponse = new CreateItemResponse(itemDto);
    return ResponseEntity.created(URI.create("/items/" + createItemResponse.getId()))
        .body(createItemResponse);
  }

  @PutMapping("/{itemId}")
  public ResponseEntity<ModifyItemResponse> modifyItem(@RequestBody ModifyItemRequest request,
      @PathVariable Long itemId) {
    ModifyItemDto modifyItemDto = new ModifyItemDto(request.getName(), request.getPrice());
    ModifiedItemDto modifiedItemDto = itemService.modifyItem(itemId, modifyItemDto);
    return ResponseEntity.ok()
        .body(new ModifyItemResponse(modifiedItemDto));
  }

}
