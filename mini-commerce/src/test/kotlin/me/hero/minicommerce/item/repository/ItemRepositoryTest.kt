package me.hero.minicommerce.item.repository

import me.hero.minicommerce.item.adapter.out.persistance.ItemRepository
import me.hero.minicommerce.item.domain.Item
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

/**
 * Spring Data JPA 자동 생성 기능 테스트
 * 목적 : 업무 중에 이와 같은 테스트가 필요한지 여부는 모르겠지만. 한 번쯤을 할 필요가 있다고 느낌.  */
@DataJpaTest
@AutoConfigureTestDatabase
@DisplayName("Spring Data JPA (ItemRepository) 테스트")
class ItemRepositoryTest(
    @Autowired private val testEntityManager: TestEntityManager,
    @Autowired private val itemRepository: ItemRepository,
) {



    @Test
    @DisplayName("상품 Insert 테스트")
    fun saveItem() {
        //given
        val 닭볶음탕 = Item(
            name = "닭볶음탕",
            price = 18000L
        )

        //when
        val savedItem = itemRepository.save(닭볶음탕)

        //then
        Assertions.assertThat(savedItem.name).isEqualTo(닭볶음탕.name)
        Assertions.assertThat(savedItem.price).isEqualTo(닭볶음탕.price)
    }

    @Test
    @DisplayName("상품 조회 테스트")
    fun findByIdTest() {
        //given
        val 닭볶음탕 = Item(
            name = "닭볶음탕",
            price = 18000L
        )
        //when
        val persist: Item = testEntityManager.persist(닭볶음탕)
        val savedItem = itemRepository.findById(persist.id).orElseGet { Item("", 0, null) }

        //then
        Assertions.assertThat(savedItem.name).isEqualTo(닭볶음탕.name)
        Assertions.assertThat(savedItem.price).isEqualTo(닭볶음탕.price)
    }
}