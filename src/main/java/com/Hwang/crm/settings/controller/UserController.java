package com.Hwang.crm.settings.controller;


import com.Hwang.crm.base.bean.ResultVo;
import com.Hwang.crm.base.exception.UserException;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/settings/user/login")
    @ResponseBody
    public ResultVo login(User user , HttpServletRequest request){
        ResultVo resultVo = new ResultVo();
//        加入ip地址
        String remoteAddr = request.getRemoteAddr();
        user.setAllowIps(remoteAddr);
        try {
            userService.login(user);

        } catch (UserException e) {
            resultVo.setMessage(e.getMessage());
            return resultVo;
        }
        resultVo.setMessage("登录成功");
        resultVo.setOk(true);
        return resultVo;
    }
}
