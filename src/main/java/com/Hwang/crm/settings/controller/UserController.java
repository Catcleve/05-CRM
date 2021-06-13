package com.Hwang.crm.settings.controller;


import com.Hwang.crm.base.bean.ResultVo;
import com.Hwang.crm.base.exception.UserException;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/settings/user/login")
    @ResponseBody
    public ResultVo login(User user , HttpServletRequest request , HttpServletResponse response){
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
        resultVo.setOk(true);
        request.getSession().setAttribute("user",user);
        return resultVo;
    }
}
