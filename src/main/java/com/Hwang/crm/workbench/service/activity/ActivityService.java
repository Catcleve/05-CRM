package com.Hwang.crm.workbench.service.activity;


import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.workbench.bean.activity.Activity;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ActivityService {
    PageInfo<Activity> list(PageInfo<Activity> pageInfo, Activity activity);

    List<User> getUser();

    void saveActivity(Activity activity);

    void updateActivityById(Activity activity);

    void deleteActivity(List<String> ids);

    void output(HttpServletResponse response);
}
