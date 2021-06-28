package com.Hwang.crm.workbench.controller.tran;

import cn.hutool.core.util.StrUtil;
import com.Hwang.crm.base.bean.ResultVo;
import com.Hwang.crm.base.bean.StageImg;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.workbench.bean.tran.Tran;
import com.Hwang.crm.workbench.bean.tran.TranHistory;
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

        return tranService.list(pageInfo,tran);
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
    public String getStage(String stage ,HttpSession session) {

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
    public List<StageImg> stage(Tran tran , HttpSession session) {

        ResultVo<List<StageImg>> resultVo = new ResultVo<>();
        List<StageImg> stage = tranService.stage(tran, session);
        try {
            resultVo.setOk(true);
            resultVo.setMessage("更新成功");
        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
//        判断是否要更换阶段
        return stage;

    }

//    显示阶段历史
    @RequestMapping("/workbench/tran/history")
    @ResponseBody
    public List<TranHistory> history(Tran tran , HttpSession session) {

        return tranService.getHistory(tran);
    }
}
