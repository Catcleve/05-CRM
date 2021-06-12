package com.Hwang.crm.base.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVo implements Serializable {

    private boolean isOk;
    private String message;
}
