package com.demo.CRUD01.response;

public class MemberGetOneResponse {

    private Long id;
    private String name;

    public MemberGetOneResponse(Long id, String name) {
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
