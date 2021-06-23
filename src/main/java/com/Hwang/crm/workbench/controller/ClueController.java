package com.Hwang.crm.workbench.controller;

import com.Hwang.crm.base.bean.DicValue;
import com.Hwang.crm.base.bean.ResultVo;
import com.Hwang.crm.base.cache.DicData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClueController {


    @RequestMapping("/workbench/clue/getDic")
    @ResponseBody
    public Map<String,List<DicValue> > getDic (HttpSession session){

        Map<String,List<DicValue> > dicMap = (HashMap<String, List<DicValue>>) session.getServletContext().getAttribute("dicMap");

        return dicMap;
    }
}
