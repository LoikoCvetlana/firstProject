package com.oraz.service;

import com.oraz.dao.MaterialDao;
import com.oraz.dto.material.CreateMaterialDto;
import com.oraz.dto.material.MaterialDto;
import com.oraz.model.Material;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MaterialService {

    private static final MaterialService INSTANCE = new MaterialService();

    public List<MaterialDto> findAll() {
        return MaterialDao.getInstance().findAll().stream()
                .map(it -> new MaterialDto(it.getId(), it.getName(), it.getDescription()))
                .collect(Collectors.toList());
    }

    public MaterialDto save(CreateMaterialDto dto) {
        Material savedMaterial = MaterialDao.getInstance().save(
                Material.builder()
                        .name(dto.getName())
                        .description(dto.getDescription())
                        .build());

        return new MaterialDto(savedMaterial.getId(), savedMaterial.getName(), savedMaterial.getDescription());
    }

    public static MaterialService getInstance() {
        return INSTANCE;
    }


}
