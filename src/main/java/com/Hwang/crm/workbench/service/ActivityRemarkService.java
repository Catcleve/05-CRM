package com.Hwang.crm.workbench.service;

import com.Hwang.crm.workbench.bean.ActivityRemark;

public interface ActivityRemarkService {


    ActivityRemark insertRemark(ActivityRemark remark);

    void deleteRemark(ActivityRemark remark);

    ActivityRemark editRemark(ActivityRemark remark);
}
