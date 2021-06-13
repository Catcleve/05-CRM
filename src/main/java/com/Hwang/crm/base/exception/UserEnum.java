package com.Hwang.crm.base.exception;

public enum UserEnum {

    LOGIN_ACCOUNT("001-01","账号或密码错误！"),
    LOGIN_EXPIRE_TIME("001-02","账号已失效！"),
    LOGIN_LOCKED("001-03","账号已被锁定"),
    LOGIN_IPS("001-04", "IP不允许");

    private String typeCode;
    private String message;

    UserEnum(String typeCode, String message) {
        this.typeCode = typeCode;
        this.message = message;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}