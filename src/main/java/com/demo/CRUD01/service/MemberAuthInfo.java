package com.demo.CRUD01.service;

public class MemberAuthInfo {

    private Long memberId;
    private String memberEmail;

    public MemberAuthInfo(Long memberId, String memberEmail) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getMemberEmail() {
        return memberEmail;
    }
}
