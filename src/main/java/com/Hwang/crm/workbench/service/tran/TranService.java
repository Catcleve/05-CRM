package com.Hwang.crm.workbench.service.tran;

import com.Hwang.crm.workbench.bean.tran.Tran;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TranService {

//    条件查询所有，带分页
    PageInfo<Tran> list(PageInfo<Tran> pageInfo, Tran tran);

//    自动补全
    List<String> queryCustomerName(String customerName);
}
