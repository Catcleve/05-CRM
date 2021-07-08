package com.Hwang.crm.workbench.controller.tran;

import cn.hutool.core.util.StrUtil;
import com.Hwang.crm.base.bean.ResultVo;
import com.Hwang.crm.base.bean.StageImg;
import com.Hwang.crm.base.exception.CrmException;
import com.test.User;
import com.Hwang.crm.workbench.bean.tran.Tran;
import com.Hwang.crm.workbench.bean.tran.TranHistory;
import com.Hwang.crm.workbench.bean.tran.TranRemark;
import com.Hwang.crm.workbench.service.tran.TranService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class TranController {

    @Autowired
    private TranService tranService;

    //    分页多条件模糊查询
    @RequestMapping("/workbench/tran/list")
    @ResponseBody
    public PageInfo<Tran> list(PageInfo<Tran> pageInfo, Tran tran) {

        return tranService.list(pageInfo, tran);
    }


    //    自动补全
    @RequestMapping("/workbench/tran/queryCustomerName")
    @ResponseBody
    public List<String> queryCustomerName(String customerName) {

        return tranService.queryCustomerName(customerName);
    }


    //  获取可能性
    @RequestMapping("/workbench/tran/getPossibility")
    @ResponseBody
    public String getStage(String stage, HttpSession session) {

        Map<String, String> stageState = (Map<String, String>) session.
                getServletContext().getAttribute("stageState");

        if (StrUtil.isNotEmpty(stage)) {
            return stageState.get(stage);
        }
        return "";
    }


    //    显示阶段图
    @RequestMapping("/workbench/tran/stage")
    @ResponseBody
    public ResultVo<List<StageImg>> stage(Tran tran, HttpSession session) {

        ResultVo<List<StageImg>> resultVo = new ResultVo<>();
        try {
            ArrayList<StageImg> stages = tranService.stage(tran, session);
            resultVo.setOk(true);
            resultVo.setMessage("更改成功");
            resultVo.setT(stages);
        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;

    }


    //    显示阶段历史
    @RequestMapping("/workbench/tran/history")
    @ResponseBody
    public List<TranHistory> history(Tran tran, HttpSession session) {

        return tranService.getHistory(tran);
    }

    //      新建交易
    @RequestMapping("/workbench/tran/insertTran")
    @ResponseBody
    public ResultVo<Object> insertTran(Tran tran, HttpSession session) {
        ResultVo<Object> resultVo = new ResultVo<>();

        User user = (User) session.getAttribute("user");

        try {
            tranService.insertTran(tran, user);
            resultVo.setOk(true);
            resultVo.setMessage("添加成功");
        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;
    }


//    显示交易备注
    @RequestMapping("/workbench/tran/tranRemarkList")
    @ResponseBody
    public PageInfo<TranRemark> tranRemarkList(String tranId, PageInfo<Object> pageInfo) {

        List<TranRemark> tranRemarks = tranService.tranRemarkList(tranId, pageInfo);

        return new PageInfo<>(tranRemarks);
    }


//  添加交易备注
    @RequestMapping("/workbench/tran/insertTranRemark")
    @ResponseBody
    public ResultVo<Object> insertTranRemark(TranRemark tranRemark,HttpSession session) {
        ResultVo<Object> resultVo = new ResultVo<>();
        String noteContent = tranRemark.getNoteContent();

        if (StrUtil.isBlankIfStr(noteContent)) {
            resultVo.setMessage("非法字符！");
            return resultVo;
        }
//        去除首尾空格
        noteContent = StrUtil.trim(noteContent);
        tranRemark.setNoteContent(noteContent);
        User user = (User) session.getAttribute("user");

        try {
            tranService.insertTranRemark(tranRemark,user);
            resultVo.setOk(true);
            resultVo.setMessage("添加成功");
        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;
    }


//    删除交易备注
    @RequestMapping("/workbench/tran/deleteRemark")
    @ResponseBody
    public ResultVo<Object> deleteRemark(String id) {

        ResultVo<Object> resultVo = new ResultVo<>();
        try {
            tranService.deleteRemark(id);
            resultVo.setOk(true);
            resultVo.setMessage("删除成功");
        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;
    }

    //    编辑市场活动备注
    @RequestMapping("/workbench/tran/updateRemark")
    @ResponseBody
    public ResultVo<Object> updateRemark(TranRemark remark, HttpSession session) {
        ResultVo<Object> resultVo = new ResultVo<>();
        User user = (User) session.getAttribute("user");
        try {
            remark.setEditBy(user.getName());
            tranService.updateRemark(remark);
            resultVo.setOk(true);
            resultVo.setMessage("修改成功");
        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;
    }
    
}
