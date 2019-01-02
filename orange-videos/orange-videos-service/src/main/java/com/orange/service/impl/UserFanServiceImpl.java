package com.orange.service.impl;

import com.orange.mapper.UsersFansMapper;
import com.orange.mapper.UsersLikeVideosMapper;
import com.orange.mapper.UsersMapper;
import com.orange.pojo.Users;
import com.orange.pojo.UsersFans;
import com.orange.pojo.UsersLikeVideos;
import com.orange.service.UserFanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class UserFanServiceImpl implements UserFanService {
    @Resource
    UsersFansMapper usersFansMapper;
    @Resource
    UsersMapper usersMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void followHim(String userId, String fanId) {
        UsersFans usersFans = new UsersFans();
        usersFans.setId(UUID.randomUUID().toString());
        usersFans.setUserId(userId);
        usersFans.setFanId(fanId);
        usersFansMapper.saveUserFan(usersFans);
        usersMapper.addFollowCounts(fanId);
        usersMapper.addFansCounts(userId);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void unFollowHim(String userId, String fanId) {
        UsersFans usersFans = new UsersFans();
        usersFans.setUserId(userId);
        usersFans.setFanId(fanId);
        usersFansMapper.deleteUserFan(usersFans);
        usersMapper.reduceFollowCounts(fanId);
        usersMapper.reduceFansCounts(userId);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean UserFan(String userId, String fanId) {
        UsersFans usersFans = new UsersFans();
        usersFans.setUserId(userId);
        usersFans.setFanId(fanId);
        UsersFans uf = usersFansMapper.findUserFan(usersFans);
        if(uf != null){
            return true;
        }
        return false;
    }
}
