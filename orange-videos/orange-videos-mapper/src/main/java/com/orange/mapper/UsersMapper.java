package com.orange.mapper;

import com.orange.pojo.Users;
import com.orange.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UsersMapper extends MyMapper<Users> {

    Users selectByUser(Users user);

    void updateByUserId(Users user);

    void addFollowCounts(@Param(value = "publisherId")String publisherId);

    void reduceFollowCounts(@Param(value="publisherId")String publisherId);

    void addFansCounts(@Param(value = "userId")String userId);

    void reduceFansCounts(@Param(value="userId")String userId);
}