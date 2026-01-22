package com.demo.CRUD01.dto;


public class ApiResponse<T> {

    private String message;
    private Integer code;
    private T data;

    public ApiResponse(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }


    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public static class MemberDto {

        private Long id;
        private String name;

        public MemberDto(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static ApiResponse<Void> exception(String message) {
            return new ApiResponse<>(message,400,null);
        }
    }
}
