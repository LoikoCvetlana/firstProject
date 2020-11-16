package com.oraz.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewOrderDto {

    private Long id;
    private LocalDate date;
    private LocalDate desireDate;
    private Integer phone;
    private String otherInformation;

}
