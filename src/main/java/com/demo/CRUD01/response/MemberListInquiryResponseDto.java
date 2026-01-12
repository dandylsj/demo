package com.demo.CRUD01.response;

import java.util.List;

public class MemberListInquiryResponseDto {

    private int count;
    private List<MemberList> memberList;

    public MemberListInquiryResponseDto(int count, List<MemberList> memberList) {
        this.count = count;
        this.memberList = memberList;
    }

    public int getCount() {
        return count;
    }

    public List<MemberList> getMemberList() {
        return memberList;
    }

    public static class MemberList {
        private Long id;
        private String name;

        public MemberList(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
