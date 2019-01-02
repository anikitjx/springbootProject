package com.orange.mapper;

import com.orange.pojo.UsersFans;
import com.orange.utils.MyMapper;

public interface UsersFansMapper extends MyMapper<UsersFans> {
    void saveUserFan(UsersFans usersFans);
    void deleteUserFan(UsersFans usersFans);
    UsersFans findUserFan(UsersFans usersFans);
}