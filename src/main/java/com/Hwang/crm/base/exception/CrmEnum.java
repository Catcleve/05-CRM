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
    REMARK_DELETE("003-002","删除备注失败，请联系管理员！"),
    REMARK_UPDATE("003-003","修改备注失败，请联系管理员！"),

    CUSTOMER_ADD("004-001","添加客户失败，请重试"),
    CUSTOMER_EDIT("004-002","删除客户失败，请重试"),
    CLUE_ADD("005-001","添加线索失败，请重试"),

    TRAN_STAGE("006-001","更新状态失败，请重试"),
    TRAN_INSERT("006-002","添加交易失败，请检查后重试"),
    TRAN_INSERT_REMARK("006-003","添加备注失败，请检查后重试"),
    TRAN_DELETE_REMARK("006-004","删除异常，请刷新后重试"),
    TRAN_UPDATE_REMARK("006-005","删除异常，请刷新后重试"),;

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
