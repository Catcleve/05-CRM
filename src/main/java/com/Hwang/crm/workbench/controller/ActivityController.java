package com.Hwang.crm.workbench.controller;

import com.Hwang.crm.base.bean.ResultVo;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.workbench.bean.Activity;
import com.Hwang.crm.workbench.service.ActivityService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

//    分页多条件模糊查询
    @RequestMapping("/workbench/activity/list")
    public PageInfo<Activity> list(PageInfo<Activity> pageInfo,Activity activity) {

        return activityService.list(pageInfo,activity);
    }

//  获取所有管理员
    @RequestMapping("/workbench/activity/getUser")
    public List<User> getUser(PageInfo<Activity> pageInfo, Activity activity) {

        return activityService.getUser();

    }

//    添加市场活动
    @RequestMapping("/workbench/activity/saveActivity")
    public ResultVo<Object> saveActivity(Activity activity) {
        ResultVo<Object> resultVo = new ResultVo<>();
        try {
            activityService.saveActivity(activity);
            resultVo.setOk(true);

        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;
    }

//  编辑市场活动
    @RequestMapping("/workbench/activity/updateActivity")
    public ResultVo<Object> updateActivity(Activity activity) {

        ResultVo<Object> resultVo = new ResultVo<>();

        try {
            activityService.updateActivityById(activity);
            resultVo.setOk(true);
        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;
    }

//    删除市场活动
    @RequestMapping("/workbench/activity/deleteActivity")
    public ResultVo<Object> deleteActivity(String[] id) {

        ResultVo<Object> resultVo = new ResultVo<>();
        List<String> ids = Arrays.asList(id);
        try {
            activityService.deleteActivity(ids);
            resultVo.setOk(true);
        } catch (CrmException crmException) {
            resultVo.setMessage(crmException.getMessage());
        }
        return resultVo;
    }
}
