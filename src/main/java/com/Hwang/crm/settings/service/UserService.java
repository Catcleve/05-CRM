package com.Hwang.crm.settings.service;

import com.Hwang.crm.settings.bean.User;

public interface UserService {

    public User login(User user);

    void verifyOldPwd(User user);
}
