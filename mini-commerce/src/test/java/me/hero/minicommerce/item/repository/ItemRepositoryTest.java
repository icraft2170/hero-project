package me.hero.minicommerce.item.repository;


import static org.assertj.core.api.Assertions.*;

import me.hero.minicommerce.item.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 * Spring Data JPA 자동 생성 기능 테스트
 * 목적 : 업무 중에 이와 같은 테스트가 필요한지 여부는 모르겠지만. 한 번쯤을 할 필요가 있다고 느낌. */
@DataJpaTest
@AutoConfigureTestDatabase
class ItemRepositoryTest {

  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private ItemRepository itemRepository;

  @Test
  @DisplayName("상품 Insert 테스트")
  void saveItem() {
    //given
    Item 닭볶음탕 = Item.builder()
        .name("닭볶음탕")
        .price(18000L)
        .build();
    //when
    Item savedItem = itemRepository.save(닭볶음탕);

    //then
    assertThat(savedItem.getName()).isEqualTo(닭볶음탕.getName());
    assertThat(savedItem.getPrice()).isEqualTo(닭볶음탕.getPrice());
  }

  @Test
  @DisplayName("상품 조회 테스트")
  void findByIdTest() {
    //given
    Item 닭볶음탕 = Item.builder()
        .name("닭볶음탕")
        .price(18000L)
        .build();
    //when
    Item persist = testEntityManager.persist(닭볶음탕);
    Item savedItem = itemRepository.findById(persist.getId()).orElseGet(() -> Item.builder().build());

    //then
    assertThat(savedItem.getName()).isEqualTo(닭볶음탕.getName());
    assertThat(savedItem.getPrice()).isEqualTo(닭볶음탕.getPrice());
  }
}