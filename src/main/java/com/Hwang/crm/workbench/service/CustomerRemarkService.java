package com.Hwang.crm.workbench.service;

import com.Hwang.crm.workbench.bean.CustomerRemark;

public interface CustomerRemarkService {


    CustomerRemark insertRemark(CustomerRemark remark);

    void deleteRemark(CustomerRemark remark);

    CustomerRemark editRemark(CustomerRemark remark);
}
