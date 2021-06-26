package com.Hwang.crm.workbench.controller.tran;

import cn.hutool.core.util.StrUtil;
import com.Hwang.crm.workbench.bean.tran.Tran;
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
    public Map<String, String> stage(Tran tran , HttpSession session) {

        String stage = tran.getStage();
        String substring = stage.substring(0, 2);
        int stageNo = Integer.parseInt(substring);

        Map<String, String> stageState = (Map<String, String>) session.
                getServletContext().getAttribute("stageState");

        Set<String> strings = stageState.keySet();

//        判断一共有多少个状态
        int index = strings.size();
//        point用来记录分割点,就是从哪里开始变成失败
        int point = 0;
        for (String string : strings) {
            point++;
            if (stageState.get(string).equals("0")) {
                break;
            }
        }

        Map<String, String> stages = new LinkedHashMap<>();

        ArrayList<String> stageName = new ArrayList<>(strings);

//      循环,次数为状态的次数
        for (int i = 1; i <= index; i++) {
//          如果当前状态和次数都小于分割点
            if (i < point && stageNo < point) {
//                如果循环次数小于状态,全是绿圈
                if (i < stageNo) {
                    System.out.println("绿圈");
                    stages.put(stageName.get(i-1),"绿圈");
//                    如果等于状态,是锚点
                } else if (i == stageNo) {
                    System.out.println("锚点");
                    stages.put(stageName.get(i-1),"锚点");
//                    如果大于当前状态,是黑圈
                } else {
                    System.out.println("黑圈");
                    stages.put(stageName.get(i-1),"黑圈");
                }
//                当前状态和次数都不小于分割点,说明是失败状态
//                小于分割点的都是黑圈
            } else if (i < point ) {
                System.out.println("黑圈");
                stages.put(stageName.get(i-1),"黑圈");
//                等于当前状态的为红叉
            } else if (stageNo == i) {
                System.out.println("红叉");
                stages.put(stageName.get(i-1),"红叉");
//                其他为黑叉
            } else  {
                System.out.println("黑叉");
                stages.put(stageName.get(i-1),"黑叉");
            }

        }
        return stages;
    }
    
}
