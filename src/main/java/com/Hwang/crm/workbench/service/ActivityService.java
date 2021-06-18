package com.Hwang.crm.workbench.service;


import com.Hwang.crm.workbench.bean.Activity;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ActivityService {
    PageInfo<Activity> list(PageInfo<Activity> pageInfo, Activity activity);
}
