package com.Hwang.crm.workbench.service.Impl.chart;

import com.Hwang.crm.base.bean.Bar;
import com.Hwang.crm.workbench.mapper.BarMapper;
import com.Hwang.crm.workbench.service.chart.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    private BarMapper barMapper;

    @Override
    public List<Bar> getTranBar() {

        List<Bar> tranBar = barMapper.getTranBar();

        return tranBar;
    }
}
