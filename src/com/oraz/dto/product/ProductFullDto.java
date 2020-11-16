package com.oraz.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductFullDto {

    private Long id;
    private String name;
    private String article;
    private String picture;
    private double value;
    private String materialName;


}
