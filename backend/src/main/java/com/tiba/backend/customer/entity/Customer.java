package com.tiba.backend.customer.entity;

import com.tiba.backend.shared.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SequenceGenerator(name = "default_seq", sequenceName = "customer_seq", allocationSize = 50)
public class Customer extends BaseEntity {

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String businessName;

  @Column(nullable = false)
  private String address;

  @Column(length = 20)
  private String phoneNumber;

  @Column(unique = true)
  private String customerCode;

  @Column(unique = true)
  private String email;

  @Min(0)
  @Max(100)
  @Column(name = "discount_percentage")
  private Integer discount;
}
