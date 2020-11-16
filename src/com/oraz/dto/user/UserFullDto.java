package com.oraz.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFullDto {

    private Long id;
    private String role;
    private String name;
    private String lastname;
    private LocalDate registrationDate;
    private String e_mail;
    private String organization;
    private String otherInformation;
}
