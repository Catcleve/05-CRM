package com.Hwang.crm.workbench.controller.chart;

import com.Hwang.crm.base.bean.ChartInfo;
import com.Hwang.crm.workbench.service.chart.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChartController {

    @Autowired
    private ChartService chartService;

//    交易柱状图
    @RequestMapping("/workbench/chart/getTranBar")
    @ResponseBody
    public ChartInfo getTranBar (){

        List<ChartInfo> chartInfos = chartService.getTranBar();
        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (ChartInfo chartInfo : chartInfos) {
            names.add(chartInfo.getName());
            values.add(chartInfo.getValue());
        }

        ChartInfo chartInfo = new ChartInfo();
        chartInfo.setNames(names);
        chartInfo.setValues(values);
        return chartInfo;
    }

//    线索饼状图
    @RequestMapping("/workbench/chart/getCluePie")
    @ResponseBody
    public List<ChartInfo> getCluePie (){

        return chartService.getCluePie();
    }

}
