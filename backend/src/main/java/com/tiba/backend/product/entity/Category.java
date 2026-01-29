package com.tiba.backend.product.entity;

import com.tiba.backend.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SequenceGenerator(name = "default_seq", sequenceName = "category_seq", allocationSize = 50)
public class Category extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String name;

  @Column(length = 500)
  private String description;
}
