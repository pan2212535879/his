package com.abc.His02.service;

import com.abc.His02.entity.User;

public interface UserCenterService {
    User passwordIsRight(Integer ID, String password);
    void changePassword(Integer ID, String password);
}
