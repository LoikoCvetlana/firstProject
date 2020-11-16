package com.oraz.service;

import com.oraz.dao.RewiewDao;
import com.oraz.dto.rewiew.RewiewDto;
import com.oraz.model.Rewiew;
import com.oraz.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RewiewService {

    private static final RewiewService INSTANCE = new RewiewService();

    public List<RewiewDto> findAll() {
        return RewiewDao.getInstance().findAll().stream()
                .map(it -> new RewiewDto(
                        it.getId(),
                        it.getUser().getName(),
                        it.getUser().getLastname(),
                        it.getUser().getOrganization(),
                        it.getText()))
                .collect(Collectors.toList());
    }

    public RewiewDao save(RewiewDto dto) {
        Rewiew savedRewiew = RewiewDao.getInstance().save(
                Rewiew.builder()
                        .id(dto.getId())
                        .text(dto.getText())
                        .user(User.builder().id(dto.getUserId()).build())
                        .build());

        return new RewiewDto(savedRewiew.getId(), savedRewiew.getText(), savedRewiew.getUserId());
    }

    public static RewiewService getInstance() {
        return INSTANCE;
    }
}