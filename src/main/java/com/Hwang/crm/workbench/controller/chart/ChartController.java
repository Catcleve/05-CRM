package com.Hwang.crm.workbench.controller.chart;

import com.Hwang.crm.base.bean.Bar;
import com.Hwang.crm.workbench.bean.tran.Tran;
import com.Hwang.crm.workbench.mapper.TranMapper;
import com.Hwang.crm.workbench.service.chart.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChartController {

    @Autowired
    private ChartService chartService;

    @RequestMapping("/workbench/chart/getTranBar")
    @ResponseBody
    public Bar getTranBar (){

        List<Bar> bars = chartService.getTranBar();
        List<String> xAxis = new ArrayList<>();
        List<Integer> datas = new ArrayList<>();
        for (Bar bar : bars) {
            xAxis.add(bar.getXAxi());
            datas.add(bar.getData());
        }

        Bar bar = new Bar();
        bar.setXAxis(xAxis);
        bar.setDatas(datas);
        return bar;
    }
}
