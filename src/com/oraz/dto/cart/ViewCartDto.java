package com.oraz.dto.cart;

import com.oraz.model.Order;
import com.oraz.model.Product;
import com.oraz.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewCartDto {

    private Order order;
    private User user;
    private Product product;
    private Integer amount;
}
