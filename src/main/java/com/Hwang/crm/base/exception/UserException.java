package com.Hwang.crm.base.exception;

public class UserException extends RuntimeException{


    private UserEnum userEnum;


    public UserException(UserEnum userEnum) {
        super(userEnum.getMessage());
    }
}
