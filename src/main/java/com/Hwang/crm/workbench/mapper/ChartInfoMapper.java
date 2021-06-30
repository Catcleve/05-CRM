package com.Hwang.crm.workbench.mapper;

import com.Hwang.crm.base.bean.ChartInfo;

import java.util.List;

public interface ChartInfoMapper {

    List<ChartInfo> getTranBar();

    List<ChartInfo> getCluePie();

}
