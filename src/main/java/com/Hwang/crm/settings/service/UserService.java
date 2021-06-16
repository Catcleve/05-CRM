package com.Hwang.crm.settings.service;

import com.Hwang.crm.settings.bean.User;

public interface UserService {

    User login(User user);

    void verifyOldPwd(User user);

    void updatePwd(User user);
}
