package com.demo.CRUD01.response;

public class MemberUpdateResponseDto {

    private Long id;

    public MemberUpdateResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
