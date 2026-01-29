package com.tiba.backend.product.entity;

import com.tiba.backend.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SequenceGenerator(name = "default_seq", sequenceName = "product_seq", allocationSize = 50)
public class Product extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Column(length = 1000)
  private String description;

  @Column(nullable = false, unique = true)
  private String code;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(nullable = false)
  private boolean isDiscounted;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;
}
