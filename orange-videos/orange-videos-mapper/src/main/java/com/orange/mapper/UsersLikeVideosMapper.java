package com.orange.mapper;

import com.orange.pojo.UsersLikeVideos;
import com.orange.utils.MyMapper;

public interface UsersLikeVideosMapper extends MyMapper<UsersLikeVideos> {
    void saveUserLikeVideo(UsersLikeVideos usersLikeVideos);
    void deleteUserLikeVideo(UsersLikeVideos usersLikeVideos);
    UsersLikeVideos findUserLikeVideo(UsersLikeVideos usersLikeVideos);
}