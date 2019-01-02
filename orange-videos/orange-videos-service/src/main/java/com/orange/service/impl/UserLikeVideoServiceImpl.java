package com.orange.service.impl;

import com.orange.mapper.UsersLikeVideosMapper;
import com.orange.mapper.VideosDtoMapper;
import com.orange.pojo.UsersLikeVideos;
import com.orange.pojo.Videos;
import com.orange.service.UserLikeVideoService;
import com.orange.utils.enums.ResultEnum;
import com.orange.utils.exception.MyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class UserLikeVideoServiceImpl implements UserLikeVideoService {
    @Resource
    VideosDtoMapper videosDtoMapper;
    @Resource
    UsersLikeVideosMapper usersLikeVideosMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void likeVideo(String userId,String videoId) {
        UsersLikeVideos usersLikeVideos = new UsersLikeVideos();
        usersLikeVideos.setId(UUID.randomUUID().toString());
        usersLikeVideos.setUserId(userId);
        usersLikeVideos.setVideoId(videoId);
        videosDtoMapper.addVideoLikeCount(videoId);
        usersLikeVideosMapper.saveUserLikeVideo(usersLikeVideos);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void unLikeVideo(String userId,String videoId) {
        UsersLikeVideos usersLikeVideos = new UsersLikeVideos();
        usersLikeVideos.setUserId(userId);
        usersLikeVideos.setVideoId(videoId);
        videosDtoMapper.reduceVideoLikeCount(videoId);
        usersLikeVideosMapper.deleteUserLikeVideo(usersLikeVideos);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean userLikeVideo(String userId, String videoId) {
        UsersLikeVideos usersLikeVideos = new UsersLikeVideos();
        usersLikeVideos.setUserId(userId);
        usersLikeVideos.setVideoId(videoId);
        UsersLikeVideos ulv=usersLikeVideosMapper.findUserLikeVideo(usersLikeVideos);
        if(ulv != null){
            return true;
        }
        return false;
    }
}
