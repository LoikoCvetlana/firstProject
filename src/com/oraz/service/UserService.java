package com.oraz.service;

import com.oraz.dao.UserDao;
import com.oraz.dto.LoginDto;
import com.oraz.dto.user.UserBasicDto;
import com.oraz.dto.user.UserFullDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserService {

    private static final UserService INSTANCE = new UserService();

    public static Optional<LoginDto> login(LoginDto loginDto) {
        return Optional.ofNullable(loginDto);
    }

    public List<UserBasicDto> findAll() {
        return UserDao.getInstance().findAll().stream()
                .map(it -> new UserBasicDto(it.getId(), it.getRole(), it.getName(), it.getLastname(), it.getRegistrationDate(), it.getOrganization()))
                .collect(Collectors.toList());
    }

    public UserFullDto findById(Long userId) {
        return UserDao.getInstance().findById(userId)
                .map(it -> UserFullDto.builder()
                        .id(it.getId())
                        .role(it.getRole().getDescription())
                        .name(it.getName())
                        .lastname(it.getLastname())
                        .registrationDate(it.getRegistrationDate())
                        .e_mail(it.getE_mail())
                        .organization(it.getOrganization())
                        .otherInformation(it.getOtherInformation())
                        .build())
                .orElse(null);
                  }

    public static UserService getInstance() {
        return INSTANCE;
    }
}