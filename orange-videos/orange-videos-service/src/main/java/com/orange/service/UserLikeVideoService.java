package com.orange.service;

import com.orange.pojo.UsersLikeVideos;

public interface UserLikeVideoService {
    /*
     *点赞视频
     */
    void likeVideo(String userId,String videoId);
    /*
     *取消保存视频
     */
    void unLikeVideo(String userId,String videoId);
    /*
     *查询是否存在点赞关系
     */
    boolean userLikeVideo(String userId,String videoId);
}
