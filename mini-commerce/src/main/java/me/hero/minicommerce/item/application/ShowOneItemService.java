package me.hero.minicommerce.item.application;

import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.application.port.in.ShowOneItemUseCase;
import me.hero.minicommerce.item.application.port.in.dto.FindItemDto;
import me.hero.minicommerce.item.application.port.out.ShowOneItemPort;
import me.hero.minicommerce.item.domain.Item;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShowOneItemService implements ShowOneItemUseCase {
  private final ShowOneItemPort showOneItemPort;

  @Override
  public FindItemDto showOneItem(long itemId) {
    Item findItem = showOneItemPort.findOneItem(itemId)
        .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다."));
    return new FindItemDto(findItem);
  }

}
