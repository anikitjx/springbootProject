package com.orange.service;

import com.orange.pojo.Users;
import com.orange.pojo.dto.UsersDto;

public interface UserService {
    /**
     *判断用户名是否存在
     */
    boolean UsernameIsExist(String username);
    /**
     * 保存用户
     */
    void saveUser(Users user);
    /**
     * 判断用户是否存在
     */
    Users UserIsExist(String username, String password);
    /**
     * 设置用户token
     */
    UsersDto setUserToken(Users user) throws Exception;
    /**
     * 更新用户信息
     */
    void updateUser(Users user);
    /**
     * 查询用户信息
     */
    Users findUserByUserId(String userId);

}
