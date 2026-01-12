package com.demo.CRUD01.exception;


public final class ValidationMessage {

    private ValidationMessage() {}

    public static final String MEMBER_NAME_NOT_BLANK = "이름은 필수입니다.";
    public static final String MEMBER_NAME_SIZE = "이름은 10자 이하 입니다.";
}
