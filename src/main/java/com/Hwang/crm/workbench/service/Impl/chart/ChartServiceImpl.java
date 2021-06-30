package com.Hwang.crm.workbench.service.Impl.chart;

import com.Hwang.crm.base.bean.ChartInfo;
import com.Hwang.crm.workbench.mapper.ChartInfoMapper;
import com.Hwang.crm.workbench.service.chart.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    private ChartInfoMapper chartInfoMapper;

    @Override
    public List<ChartInfo> getTranBar() {

        return chartInfoMapper.getTranBar();
    }

    @Override
    public List<ChartInfo> getCluePie() {
        return chartInfoMapper.getCluePie();
    }
}
