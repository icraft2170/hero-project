package me.hero.minicommerce.item.adapter.in.web;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.adapter.in.web.dto.CreateItemRequest;
import me.hero.minicommerce.item.adapter.in.web.dto.CreateItemResponse;
import me.hero.minicommerce.item.adapter.in.web.dto.FindItemResponse;
import me.hero.minicommerce.item.adapter.in.web.dto.ModifyItemRequest;
import me.hero.minicommerce.item.adapter.in.web.dto.ModifyItemResponse;
import me.hero.minicommerce.item.application.port.in.CreateItemUseCase;
import me.hero.minicommerce.item.application.port.in.DeleteItemUseCase;
import me.hero.minicommerce.item.application.port.in.ModifyItemUseCase;
import me.hero.minicommerce.item.application.port.in.ShowOneItemUseCase;
import me.hero.minicommerce.item.application.port.in.dto.CreateItemDto;
import me.hero.minicommerce.item.application.port.in.dto.FindItemDto;
import me.hero.minicommerce.item.application.port.in.dto.ModifiedItemDto;
import me.hero.minicommerce.item.application.port.in.dto.ModifyItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

  private final DeleteItemUseCase deleteItemUseCase;
  private final CreateItemUseCase createItemUseCase;
  private final ShowOneItemUseCase showOneItemUseCase;

  private final ModifyItemUseCase modifyItemUseCase;


  @PostMapping("")
  public ResponseEntity<CreateItemResponse> createItem(@RequestBody CreateItemRequest request) {
    CreateItemDto itemDto = createItemUseCase.createItem(request.toDto());
    CreateItemResponse createItemResponse = new CreateItemResponse(itemDto);
    return ResponseEntity.created(URI.create("/items/" + createItemResponse.getId()))
        .body(createItemResponse);
  }

  @PutMapping("/{itemId}")
  public ResponseEntity<ModifyItemResponse> modifyItem(@RequestBody ModifyItemRequest request,
      @PathVariable Long itemId) {
    ModifyItemDto modifyItemDto = new ModifyItemDto(request.getName(), request.getPrice());
    ModifiedItemDto modifiedItemDto = modifyItemUseCase.modifyItem(itemId, modifyItemDto);
    return ResponseEntity.ok()
        .body(new ModifyItemResponse(modifiedItemDto));
  }

  @GetMapping("/{itemId}")
  public ResponseEntity<FindItemResponse> findItem(@PathVariable long itemId) {
    FindItemDto findItem = showOneItemUseCase.showOneItem(itemId);
    return ResponseEntity.ok()
        .body(new FindItemResponse(findItem));
  }

  @DeleteMapping("/{itemId}")
  public ResponseEntity<Void> deleteItem(@PathVariable long itemId) {
    deleteItemUseCase.deleteItem(itemId);
    return ResponseEntity.ok()
        .body(null);
  }

}
