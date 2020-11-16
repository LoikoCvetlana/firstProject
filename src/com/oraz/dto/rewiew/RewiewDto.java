package com.oraz.dto.rewiew;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewiewDto {

    private Long id;
    private String userName;
    private String userLastname;
    private String organization;
    private String text;

}
