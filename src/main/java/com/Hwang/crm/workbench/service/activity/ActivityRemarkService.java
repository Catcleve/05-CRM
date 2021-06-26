package com.Hwang.crm.workbench.service.activity;

import com.Hwang.crm.workbench.bean.activity.ActivityRemark;

public interface ActivityRemarkService {


    ActivityRemark insertRemark(ActivityRemark remark);

    void deleteRemark(ActivityRemark remark);

    ActivityRemark editRemark(ActivityRemark remark);
}
