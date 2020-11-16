package com.oraz.model.enums;

import lombok.Getter;

@Getter
public enum Role {
    USER ("user"), ADMIN("admin");

    private String description;

    Role (String description) {
        this.description = description;
    }

}
