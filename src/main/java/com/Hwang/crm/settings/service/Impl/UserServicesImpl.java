package com.Hwang.crm.settings.service.Impl;

import com.Hwang.crm.base.exception.CrmEnum;
import com.Hwang.crm.base.exception.CrmException;
import com.Hwang.crm.base.util.DateTimeUtil;
import com.Hwang.crm.base.util.MD5Util;
import com.test.User;
import com.Hwang.crm.settings.mapper.UserMapper;
import com.Hwang.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServicesImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


//    登录
    public User login(User user) {

//        加密密码
        String loginPwd = user.getLoginPwd();
        String md5 = MD5Util.getMD5(loginPwd);
        user.setLoginPwd(md5);
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("loginAct", user.getLoginAct())
                .andEqualTo("loginPwd", user.getLoginPwd());
        List<User> users = userMapper.selectByExample(example);

//        这里只验证了账号和密码，如果集合长度为0，则说明账号密码错误
        if (users.size() == 0) {
            throw new CrmException(CrmEnum.LOGIN_ACCOUNT);
        } else {
            User user1 = users.get(0);
            //        验证是否失效
            if (user1.getExpireTime().compareTo(DateTimeUtil.getSysTime()) < 0) {
                throw new CrmException(CrmEnum.LOGIN_EXPIRE_TIME);
            }
//            验证是否被锁定
            if ("0".equals(user1.getLockState())) {
                throw new CrmException(CrmEnum.LOGIN_LOCKED);
            }
//            验证ip地址是否允许
            if (!user1.getAllowIps().contains(user.getAllowIps())) {
                throw new CrmException(CrmEnum.LOGIN_IPS);
            }
        }
        return users.get(0);
    }


//    验证旧密码
    @Override
    public void verifyOldPwd(User user) {
        List<User> users = userMapper.select(user);
        if (users.size() == 0) {
            throw new CrmException(CrmEnum.LOGIN_verifyOldPwd);
        }
    }


//    修改密码
    @Override
    public void updatePwd(User user) {
        user.setLoginPwd(MD5Util.getMD5(user.getLoginPwd()));

        userMapper.updateByPrimaryKeySelective(user);


    }

    @Override
    public void updatePho(User user) {

        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i == 0) {
            throw new CrmException(CrmEnum.CHANGE_IMG);
        }
    }
}
