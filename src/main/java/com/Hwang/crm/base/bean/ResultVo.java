package com.Hwang.crm.base.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVo<T> implements Serializable {

    private boolean isOk;
    private String message;
    private T t;
}
