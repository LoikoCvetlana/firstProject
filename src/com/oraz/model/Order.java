package com.oraz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    private Long id;
    private LocalDate date;
    private LocalDate desireDate;
    private Integer phone;
    private String otherInformation;
}
