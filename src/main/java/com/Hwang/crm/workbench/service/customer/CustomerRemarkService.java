package com.Hwang.crm.workbench.service.customer;

import com.Hwang.crm.workbench.bean.customer.CustomerRemark;

public interface CustomerRemarkService {


    CustomerRemark insertRemark(CustomerRemark remark);

    void deleteRemark(CustomerRemark remark);

    CustomerRemark editRemark(CustomerRemark remark);
}
