package com.Hwang.crm.workbench.service.tran;

import com.Hwang.crm.base.bean.StageImg;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.workbench.bean.tran.Tran;
import com.Hwang.crm.workbench.bean.tran.TranHistory;
import com.Hwang.crm.workbench.bean.tran.TranRemark;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public interface TranService {

//    条件查询所有，带分页
    PageInfo<Tran> list(PageInfo<Tran> pageInfo, Tran tran);

//    自动补全
    List<String> queryCustomerName(String customerName);

//    交易历史
    List<TranHistory> getHistory(Tran tran);

//    交易阶段图片显示
    ArrayList<StageImg> stage(Tran tran, HttpSession session);

//    新建交易
    void insertTran(Tran tran, User user);

//    显示交易备注列表
    List<TranRemark> tranRemarkList(String tranId,PageInfo<Object> pageInfo);

//    添加交易备注
    void insertTranRemark(TranRemark tranRemark, User user);

//    删除交易备注
    void deleteRemark(String id);

//    编辑交易备注
    void updateRemark(TranRemark remark);
}
