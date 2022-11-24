package me.hero.minicommerce.item.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hero.minicommerce.item.adapter.out.persistance.dto.ItemModifyParams;
import org.hibernate.annotations.Comment;

@NoArgsConstructor
@Getter
@Entity
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Comment("상품 명")
  @Column(nullable = false)
  private String name;
  @Comment("상품 가격")
  private Long price;

  @Builder
  public Item(String name, Long price) {
    this.name = name;
    this.price = price;
  }

  public void modify(ItemModifyParams params) {
    this.name = params.getName();
    this.price = params.getPrice();
  }
}
