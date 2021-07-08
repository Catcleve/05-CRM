package com.Hwang.crm.settings.service;

import com.test.User;

public interface UserService {

    User login(User user);

    void verifyOldPwd(User user);

    void updatePwd(User user);

    void updatePho(User user);
}
