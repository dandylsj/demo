package com.demo.CRUD01.request;

import com.demo.CRUD01.exception.ValidationMessage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MemberResiterRequestDto {

    @NotBlank(message = ValidationMessage.MEMBER_NAME_NOT_BLANK)
    @Size(max = 10 , message = ValidationMessage.MEMBER_NAME_SIZE)
    private final String name;
    private final String email;
    private final String password;

    @JsonCreator
    public MemberResiterRequestDto(
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password
            ){
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
