package com.demo.CRUD01.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class MemberUpdateRequestDto {

    private String name;

    @JsonCreator
    public MemberUpdateRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
