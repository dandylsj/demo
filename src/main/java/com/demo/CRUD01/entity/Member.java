package com.demo.CRUD01.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @Column(name = "email", length =  200, nullable = false)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "createdAt",nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;
    @Column(name = "isDeleted",nullable = false)
    private boolean isDeleted = false;


    public Member() {

    }

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isDeleted = false;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Long getId() {
        return id;
    }

    //이름 수정
    public String updateMemberName(String newName) {
        this.name = newName;
        this.updatedAt = LocalDateTime.now();
        return name;
    }

    /**
     * 비밀번호 검증
     * @return
     */
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void softDeleted() {
    }
}
