package com.Hwang.crm.base.exception;

public enum CrmEnum {

    LOGIN_ACCOUNT("001-01","账号或密码错误！"),
    LOGIN_EXPIRE_TIME("001-02","账号已失效！"),
    LOGIN_LOCKED("001-03","账号已被锁定"),
    LOGIN_IPS("001-04", "IP不允许"),
    LOGIN_verifyOldPwd("001-05", "原密码错误"),
    UPLOAD_SUFFIX("001-006","文件类型错误"),
    UPLOAD_SIZE("001-007","图片过大！"),
    CHANGE_IMG("001-008","头像修改失败，请联系管理员！"),
    ACTIVITY_ADD("002-001","添加活动失败，请联系管理员！"),
    ACTIVITY_EDIT("002-002","修改活动失败，请联系管理员！"),
    ACTIVITY_DELETE("002-003","删除失败，请刷新后重试！"),
    REMARK_INSERT("003-001","添加备注失败，请联系管理员！"),
    REMARK_DELETE("003-001","删除备注失败，请联系管理员！"),
    REMARK_UPDATE("003-001","修改备注失败，请联系管理员！"),
    CUSTOMER_ADD("002-001","添加客户失败，请重试"),
    CUSTOMER_EDIT("002-001","删除客户失败，请重试"),;

    private String typeCode;
    private String message;

    CrmEnum(String typeCode, String message) {
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
