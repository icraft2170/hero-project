package me.hero.minicommerce.item.application;

import lombok.RequiredArgsConstructor;
import me.hero.minicommerce.item.application.port.in.DeleteItemUseCase;
import me.hero.minicommerce.item.application.port.out.DeleteItemPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteItemService implements DeleteItemUseCase {

  private final DeleteItemPort deleteItemPort;

  @Override
  public void deleteItem(long itemId) {
    deleteItemPort.deleteItem(itemId);
  }
}
