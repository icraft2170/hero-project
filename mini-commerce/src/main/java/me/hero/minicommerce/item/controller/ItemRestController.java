package me.hero.minicommerce.item.controller;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.controller.dto.CreateItemRequest;
import me.hero.minicommerce.item.controller.dto.CreateItemResponse;
import me.hero.minicommerce.item.service.ItemService;
import me.hero.minicommerce.item.service.dto.CreateItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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


}
