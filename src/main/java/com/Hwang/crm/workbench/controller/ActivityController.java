package com.Hwang.crm.workbench.controller;

import com.Hwang.crm.workbench.bean.Activity;
import com.Hwang.crm.workbench.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/workbench/activity/list")
    public PageInfo<Activity> list(PageInfo<Activity> pageInfo,Activity activity) {


//        分页助手

        PageInfo<Activity> list = activityService.list(pageInfo,activity);
        return list;
    }
}
