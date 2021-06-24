package com.Hwang.crm.workbench.controller;

import com.Hwang.crm.base.bean.ResultVo;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.workbench.bean.CustomerRemark;
import com.Hwang.crm.workbench.service.CustomerRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerRemarkController {

    @Autowired
    private CustomerRemarkService remarkService;

//    添加客户备注
    @RequestMapping("/workbench/customer/insertRemark")
    @ResponseBody
    public ResultVo<CustomerRemark> insertRemark(CustomerRemark remark, HttpSession session) {
        ResultVo<CustomerRemark> resultVo = new ResultVo<>();
        User user = (User) session.getAttribute("user");
        remark.setCreateBy(user.getId());
        try {
            remark = remarkService.insertRemark(remark);
            remark.setCreateBy(user.getName());
            remark.setImg(user.getImg());
            resultVo.setOk(true);
            resultVo.setT(remark);
        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;
    }

    //    删除客户备注
    @RequestMapping("/workbench/customer/deleteRemark")
    @ResponseBody
    public ResultVo<Object> deleteRemark(CustomerRemark remark) {
        ResultVo<Object> resultVo = new ResultVo<>();

        try {
            remarkService.deleteRemark(remark);
            resultVo.setOk(true);
        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;
    }


    //    编辑客户备注
    @RequestMapping("/workbench/customer/updateRemark")
    @ResponseBody
    public ResultVo<CustomerRemark> editRemark(CustomerRemark remark, HttpSession session) {
        ResultVo<CustomerRemark> resultVo = new ResultVo<>();
        User user = (User) session.getAttribute("user");
        try {
            remark.setEditBy(user.getName());
            remark = remarkService.editRemark(remark);
            resultVo.setOk(true);
            resultVo.setT(remark);
        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;
    }
}
