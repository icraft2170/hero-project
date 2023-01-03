package me.hero.minicommerce.item.domain

import me.hero.minicommerce.item.adapter.out.persistance.dto.ItemModifyParams
import org.hibernate.annotations.Comment
import javax.persistence.*

@Entity
class Item (

    @Comment("상품 명")
    @Column(nullable = false)
    var name: String,
    @Comment("상품 가격")
    var price: Long,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
) {

    fun modify(params: ItemModifyParams) {
        name = params.name
        price = params.price
    }
}