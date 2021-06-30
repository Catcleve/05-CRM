package com.Hwang.crm.base.bean;

import lombok.Data;

import java.util.List;

@Data
public class ChartInfo {

    private String name;
    private Integer value;
    private List<String> names;
    private List<Integer> values;

}
