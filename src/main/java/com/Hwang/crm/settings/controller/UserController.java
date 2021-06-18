package com.Hwang.crm.settings.controller;


import com.Hwang.crm.base.bean.ResultVo;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.base.util.MD5Util;
import com.Hwang.crm.base.util.UploadUtil;
import com.Hwang.crm.settings.bean.User;
import com.Hwang.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //    登录方法
    @RequestMapping("/settings/user/login")
    @ResponseBody
    public ResultVo login(User user, HttpServletRequest request) {
        ResultVo resultVo = new ResultVo();
//        加入ip地址
        String remoteAddr = request.getRemoteAddr();
        user.setAllowIps(remoteAddr);
        try {
            user = userService.login(user);
            request.getSession().setAttribute("user", user);

        } catch (CrmException e) {
            resultVo.setMessage(e.getMessage());
            return resultVo;
        }
        resultVo.setOk(true);
        return resultVo;
    }

    //    登出方法
    @RequestMapping("settings/user/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/index.jsp";
    }

    //    异步验证原密码
    @RequestMapping("/settings/user/verifyOldPwd")
    @ResponseBody
    public ResultVo verifyOldPwd(String loginPwd, HttpSession session) {
        ResultVo resultVo = new ResultVo();
        User user = (User) session.getAttribute("user");
        if (user == null) {
//            登录页面停留时间过长，session失效
            System.out.println("user = " + user);
        } else {
            User uuser = new User();
            uuser.setLoginAct(user.getLoginAct());
            uuser.setLoginPwd(MD5Util.getMD5(loginPwd));
            try {
                userService.verifyOldPwd(uuser);

            } catch (CrmException e) {
                resultVo.setMessage(e.getMessage());
                resultVo.setOk(false);
                return resultVo;
            }
        }
        resultVo.setOk(true);
        return resultVo;
    }

    //    文件上传
    @RequestMapping("/settings/user/upload")
    @ResponseBody
    public ResultVo upload(MultipartFile[] img, HttpServletRequest request) {

        return UploadUtil.fileUpload(img, request);
    }

    //    修改密码
    @RequestMapping("/settings/user/changePwd")
    @ResponseBody
    public ResultVo upload(String newPwd, HttpSession session) {
        ResultVo resultVo = new ResultVo();
        User user = (User) session.getAttribute("user");
        user.setLoginPwd(newPwd);
        try {
            userService.updatePwd(user);
            resultVo.setOk(true);
        } catch (CrmException e) {
        }
        return resultVo;
    }


//    修改头像
    @RequestMapping("/settings/user/changePho")
    @ResponseBody
    public ResultVo<String> changePho(String img, HttpSession session) {
        ResultVo<String> resultVo = new ResultVo<>();
        User user = (User) session.getAttribute("user");
        user.setImg(img);
        try {
            userService.updatePho(user);
            resultVo.setOk(true);
            resultVo.setMessage("头像修改成功");
        } catch (CrmException e) {
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }

}
