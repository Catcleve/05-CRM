package com.Hwang.crm.base.exception;

public class CrmException extends RuntimeException{


    private CrmEnum userEnum;


    public CrmException(CrmEnum userEnum) {
        super(userEnum.getMessage());
    }
}
