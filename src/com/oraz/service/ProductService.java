package com.oraz.service;

import com.oraz.dao.ProductDao;
import com.oraz.dto.product.CreateProductDto;
import com.oraz.dto.product.ProductBasicDto;
import com.oraz.dto.product.ProductFullDto;
import com.oraz.model.Material;
import com.oraz.model.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductService {

    private static final ProductService INSTANCE = new ProductService();

    public List<ProductBasicDto> findAll() {
        return ProductDao.getInstance().findAll().stream()
                .map(it -> new ProductBasicDto(it.getId(), it.getName(), it.getArticle(), it.getPicture()))
                       .collect(Collectors.toList());
    }


    public ProductFullDto findById(Long productId) {
        return ProductDao.getInstance().findById(productId)
                .map(it -> ProductFullDto.builder()
                        .name(it.getName())
                        .article(it.getArticle())
                        .materialName(it.getMaterial().getName())
                        .value(it.getValue())
                        .picture(it.getPicture())
                        .build())
                .orElse(null);
    }

    public ProductBasicDto save(CreateProductDto dto) {
        Product savedProduct = ProductDao.getInstance().save(
                Product.builder()
                        .name(dto.getName())
                        .article(dto.getArticle())
                        .picture(dto.getPicture())
                        .value(dto.getValue())
                        .material(Material.builder().id(dto.getMaterialId()).build())
                        .build());

        return new ProductBasicDto(savedProduct.getId(), savedProduct.getName(), savedProduct.getArticle(), savedProduct.getPicture());
    }

    public static ProductService getInstance() {
        return INSTANCE;
    }
}