package com.Hwang.crm.workbench.controller.clue;

import com.Hwang.crm.base.bean.DicValue;
import com.Hwang.crm.base.bean.ResultVo;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.workbench.bean.activity.Activity;
import com.Hwang.crm.workbench.bean.clue.Clue;
import com.Hwang.crm.workbench.bean.relation.ClueActivityRelation;
import com.Hwang.crm.workbench.bean.tran.Tran;
import com.Hwang.crm.workbench.service.clue.ClueService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClueController {


    @Autowired
    private ClueService clueService;
    
    //  获取字典到页面
    @RequestMapping("/workbench/clue/getDic")
    @ResponseBody
    public Map<String,List<DicValue> > getDic (HttpSession session){

        Map<String,List<DicValue> > dicMap = (HashMap<String, List<DicValue>>) session.getServletContext().getAttribute("dicMap");

        return dicMap;
    }


    //    分页多条件模糊查询
    @RequestMapping("/workbench/clue/list")
    @ResponseBody
    public PageInfo<Clue> list(PageInfo<Clue> pageInfo, Clue clue) {

        return clueService.list(pageInfo,clue);
    }
    
//    添加线索
    @RequestMapping("/workbench/clue/insertClue")
    @ResponseBody
    public ResultVo<Object> insertClue(Clue clue, HttpSession session) {
        ResultVo<Object> resultVo = new ResultVo<>();
        User user = (User) session.getAttribute("user");
        try {
            clue.setCreateBy(user.getName());
            clueService.saveClue(clue);
            resultVo.setOk(true);

        } catch (CrmException c) {
            resultVo.setMessage(c.getMessage());
        }
        return resultVo;
    }

//    查询绑定的市场活动
    @RequestMapping("/workbench/clue/getActivity")
    @ResponseBody
    public PageInfo<Activity> getActivity(Clue clue,PageInfo<Activity> pageInfo) {

        List<Activity> activity = clueService.getActivity(clue,pageInfo);

        return new PageInfo<>(activity);

    }

//    解除市场活动绑定
    @RequestMapping("/workbench/clue/unBind")
    @ResponseBody
    public ResultVo<Object> unBind(ClueActivityRelation relation) {
        ResultVo<Object> resultVo = new ResultVo<>();
        clueService.deleteRelation(relation);

        return resultVo;

    }

//    绑定市场活动
    @RequestMapping("/workbench/clue/bindActivities")
    @ResponseBody
    public ResultVo<Object> bindActivities(String clueId , String[] ids) {
        ResultVo<Object> resultVo = new ResultVo<>();
        clueService.insertRelation(clueId,ids);

        return resultVo;

    }

//    转换 tran对象中 id值为对应的线索clue的id值，createTime对应的为是否创建交易
    @RequestMapping("/workbench/clue/conversion")
    @ResponseBody
    public ResultVo<Object> conversion(Tran tran, HttpSession session) {

        ResultVo<Object> resultVo = new ResultVo<>();
        clueService.insertConversion(tran,session);

        return resultVo;

    }


}
