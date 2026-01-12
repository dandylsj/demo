package com.demo.CRUD01.dto;

public class JwtMemberPayload {

    private Long memberId;
    private String email;

    public JwtMemberPayload(Long memberId, String email) {
        this.memberId = memberId;
        this.email = email;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getEmail() {
        return email;
    }
}
