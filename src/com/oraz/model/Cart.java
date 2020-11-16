package com.oraz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    private Long id;
    private User user;
    private Product product;
    private Boolean availability;
    private Integer amount;
    private Order order;
}
