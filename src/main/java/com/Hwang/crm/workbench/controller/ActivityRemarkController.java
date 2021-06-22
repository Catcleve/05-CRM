package com.Hwang.crm.workbench.controller;

import com.Hwang.crm.base.bean.ResultVo;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.workbench.bean.ActivityRemark;
import com.Hwang.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ActivityRemarkController {

    @Autowired
    private ActivityRemarkService remarkService;

    @RequestMapping("/workbench/activity/insertRemark")
    @ResponseBody
    public ResultVo<ActivityRemark> insertRemark(ActivityRemark remark, HttpSession session){
        ResultVo<ActivityRemark> resultVo = new ResultVo<>();
        User user = (User) session.getAttribute("user");
        remark.setCreateBy(user.getName());
        remark.setOwner(user.getId());
        try {
            remark = remarkService.insertRemark(remark);
            remark.setImg(user.getImg());
            resultVo.setOk(true);
            resultVo.setT(remark);
        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;
    }
}
