package com.Hwang.crm.workbench.service.tran;

import com.Hwang.crm.base.bean.StageImg;
import com.Hwang.crm.workbench.bean.tran.Tran;
import com.Hwang.crm.workbench.bean.tran.TranHistory;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TranService {

//    条件查询所有，带分页
    PageInfo<Tran> list(PageInfo<Tran> pageInfo, Tran tran);

//    自动补全
    List<String> queryCustomerName(String customerName);

//    交易历史
    List<TranHistory> getHistory(Tran tran);

    ArrayList<StageImg> stage(Tran tran, HttpSession session);
}
