package com.Hwang.crm.workbench.controller.activity;

import com.Hwang.crm.base.bean.ResultVo;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.workbench.bean.activity.ActivityRemark;
import com.Hwang.crm.workbench.service.activity.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ActivityRemarkController {

    @Autowired
    private ActivityRemarkService remarkService;

//    添加市场活动备注
    @RequestMapping("/workbench/activity/insertRemark")
    @ResponseBody
    public ResultVo<ActivityRemark> insertRemark(ActivityRemark remark, HttpSession session) {
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

    //    删除市场活动备注
    @RequestMapping("/workbench/activity/deleteRemark")
    @ResponseBody
    public ResultVo<Object> deleteRemark(ActivityRemark remark) {
        ResultVo<Object> resultVo = new ResultVo<>();

        try {
            remarkService.deleteRemark(remark);
            resultVo.setOk(true);
        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;
    }


    //    编辑市场活动备注
    @RequestMapping("/workbench/activity/updateRemark")
    @ResponseBody
    public ResultVo<ActivityRemark> editRemark(ActivityRemark remark, HttpSession session) {
        ResultVo<ActivityRemark> resultVo = new ResultVo<>();
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
