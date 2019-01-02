package com.orange.service.impl;

import com.orange.mapper.UsersMapper;
import com.orange.pojo.Users;
import com.orange.pojo.dto.UsersDto;
import com.orange.service.UserService;
import com.orange.utils.TokenGenerator;
import com.orange.utils.redis.RedisOperator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UsersMapper usersMapper;

    @Resource
    private RedisOperator redis;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean UsernameIsExist(String username) {
        Users user = new Users();
        user.setUsername(username);

        Users result = usersMapper.selectByUser(user);
        return result == null?false:true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveUser(Users user) {
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        usersMapper.insert(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users UserIsExist(String username, String password) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);

        Users result = usersMapper.selectByUser(user);
        return result;
    }

    @Override
    public UsersDto setUserToken(Users user) throws Exception {
        TokenGenerator tokenGenerator = new TokenGenerator();
        String authToken = tokenGenerator.generate(user.getUsername());
        redis.set(user.getId(),authToken,60*60*24*7);

        UsersDto userDto = new UsersDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setUserToken(authToken);
        return userDto;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUser(Users user) {
        usersMapper.updateByUserId(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users findUserByUserId(String userId) {
        Users user = new Users();
        user.setId(userId);
        Users result = usersMapper.selectByUser(user);
        return result;
    }
}
