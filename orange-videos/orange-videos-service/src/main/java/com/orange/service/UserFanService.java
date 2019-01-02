package com.orange.service;

import com.orange.pojo.UsersFans;

public interface UserFanService {
    /*
     *关注
     */
    void followHim(String userId,String fanId);
    /*
     *取消关注
     */
    void unFollowHim(String userId,String fanId);
    /*
     *是否存在关注关系
     */
    boolean UserFan(String userId,String fanId);
}
