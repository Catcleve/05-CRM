package com.Hwang.crm.workbench.service.chart;

import com.Hwang.crm.base.bean.ChartInfo;

import java.util.List;

public interface ChartService {
    List<ChartInfo> getTranBar();

    List<ChartInfo> getCluePie();
}
