package me.hero.minicommerce.item.adapter.out.persistance

import me.hero.minicommerce.item.domain.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ItemRepository : JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i where i.id = :id")
    fun findNullableById(@Param("id") id: Long): Item?
}