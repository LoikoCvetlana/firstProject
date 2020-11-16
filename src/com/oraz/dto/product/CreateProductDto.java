package com.oraz.dto.product;

import com.oraz.model.Material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductDto {

    private Long id;
    private String name;
    private String article;
    private String picture;
    private double value;
    private Long materialId;


}
